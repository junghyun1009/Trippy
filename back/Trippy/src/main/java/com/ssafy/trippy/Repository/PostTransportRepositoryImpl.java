package com.ssafy.trippy.Repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
@RequiredArgsConstructor
public class PostTransportRepositoryImpl implements PostTransportRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final PostRepository postRepository;


    @Override
    public List<ResponsePostDto> findAllBySearch(String title, int company, Long transportId,String countryName,String cityName) {
        Set<Long> postId = new HashSet<>();
        QPostTransport transport = QPostTransport.postTransport;
        QPost post = QPost.post;
        QLocation location = QLocation.location;
        BooleanBuilder builder = new BooleanBuilder();
        if(company>0 ){
            builder.and(post.company.eq(company));
        }
        if(!StringUtils.isEmpty(title)) {
            builder.and(post.title.containsIgnoreCase(title));
        }
        if(transportId>0){
            builder.and(transport.transport.id.eq(transportId));
        }
        if(!StringUtils.isEmpty(countryName)){
            builder.and(location.countryName.containsIgnoreCase(countryName));
        }
        if(!StringUtils.isEmpty(cityName)){
            builder.and(location.cityName.containsIgnoreCase(cityName));
        }

        List<PostTransport> postList = jpaQueryFactory.selectFrom(transport)
                .join(transport.post, post)
                .join(post.location,location)
                .where(builder)
                .fetch();
        List<ResponsePostDto> responsePostDtos = new ArrayList<>();
        for (PostTransport postTransport : postList) {
            postId.add(postTransport.getPost().getId());
        }
        for (Long aLong : postId) {
            Post post1 = postRepository.findById(aLong).get();
            ResponsePostDto dto = ResponsePostDto.builder()
                    .post(post1).build();
            responsePostDtos.add(dto);
        }
        return responsePostDtos;
    }

    @Override
    public List<ResponseCommunityPostDto> findAllByCommunitySearch(String countryName, String cityName) {
        QLocation location = QLocation.location;
        QCommunityPost communityPost = QCommunityPost.communityPost;
        BooleanBuilder builder = new BooleanBuilder();
        if(!StringUtils.isEmpty(countryName)){
            builder.and(location.countryName.containsIgnoreCase(countryName));
        }
        if(!StringUtils.isEmpty(cityName)){
            builder.and(location.cityName.containsIgnoreCase(cityName));
        }
        List<CommunityPost> postList = jpaQueryFactory.selectFrom(communityPost)
                .join(communityPost.location,location)
                .where(builder)
                .fetch();

        List<ResponseCommunityPostDto> communityPostDtos = new ArrayList<>();
        for (CommunityPost post : postList) {
            ResponseCommunityPostDto communityPostDto = new ResponseCommunityPostDto(post);
            communityPostDtos.add(communityPostDto);
        }
        return communityPostDtos;
    }


}
