package com.ssafy.trippy.Repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostTransport;
import com.ssafy.trippy.Domain.QPost;
import com.ssafy.trippy.Domain.QPostTransport;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostTransportRepositoryImpl implements PostTransportRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final PostRepository postRepository;


    @Override
    public List<ResponsePostDto> findAllBySearch(String title, int company, Long locationId) {
        QPostTransport transport = QPostTransport.postTransport;
        QPost post = QPost.post;
        BooleanBuilder builder = new BooleanBuilder();
        if(company>0 ){
            builder.and(post.company.eq(company));
        }
        if(!StringUtils.isEmpty(title)) {
            builder.and(post.title.containsIgnoreCase(title));
        }
        if(locationId>0){
            builder.and(transport.transport.id.eq(locationId));
        }

        List<PostTransport> postList = jpaQueryFactory.selectFrom(transport)
                .join(transport.post, post)
                .where(builder)
                .fetch();
        List<ResponsePostDto> responsePostDtos = new ArrayList<>();
        for (PostTransport postTransport : postList) {
            Post post1 = postRepository.findById(postTransport.getPost().getId()).get();
            ResponsePostDto dto = ResponsePostDto.builder()
                    .post(post1).build();
            responsePostDtos.add(dto);
        }
        return responsePostDtos;
    }


}
