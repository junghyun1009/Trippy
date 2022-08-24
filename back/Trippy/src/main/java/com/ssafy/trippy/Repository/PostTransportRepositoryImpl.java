package com.ssafy.trippy.Repository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trippy.Domain.*;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Service.S3Uploader;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
@RequiredArgsConstructor
public class PostTransportRepositoryImpl implements PostTransportRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final S3Uploader s3Uploader;

    @Override
    public List<ResponsePostDto> findAllBySearch(String title, int company, Long transportId) {
        Set<Long> postId = new HashSet<>();
        QPostTransport transport = QPostTransport.postTransport;
        QPost post = QPost.post;
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

        List<PostTransport> postList = jpaQueryFactory.selectFrom(transport)
                .join(transport.post, post)
                .where(builder)
                .fetch();
        List<ResponsePostDto> responsePostDtos = new ArrayList<>();
        for (PostTransport postTransport : postList) {
            postId.add(postTransport.getPost().getId());
        }
        for (Long aLong : postId) {
            Post post1 = postRepository.findById(aLong).orElseThrow();
            ResponsePostDto dto = ResponsePostDto.builder()
                    .post(post1).build();
            Member member = memberRepository.findById(dto.getMemberId()).orElseThrow();
            dto.setMemberImg(s3Uploader.getS3(member.getImg_path()));
            responsePostDtos.add(dto);
        }
        return responsePostDtos;
    }


}
