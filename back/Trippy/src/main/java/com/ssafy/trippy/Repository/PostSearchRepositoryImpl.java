package com.ssafy.trippy.Repository;
import static com.ssafy.trippy.Domain.QPost.post;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostSearchRepositoryImpl implements PostSearchRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ResponsePostDto> findAllByTitleContainingAndCompany(String title, int company) {
        BooleanBuilder builder = new BooleanBuilder();
        if(company> 0 ){
            builder.and(post.company.eq(company));
        }
        List<Post> postList = jpaQueryFactory.selectFrom(post)
                .where(builder,
                        eqTitle(title))
                .fetch();
        List<ResponsePostDto> responsePostDtos = new ArrayList<>();
        for (Post post1 : postList) {
            ResponsePostDto dto = ResponsePostDto.builder()
                    .post(post1).build();
            responsePostDtos.add(dto);
        }
        return responsePostDtos;
    }

    private BooleanExpression eqTitle(String title){
        return title == "" ? null : post.title.contains(title);
    }

}
