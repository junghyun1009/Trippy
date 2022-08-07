package com.ssafy.trippy.Repository;

import static com.ssafy.trippy.Domain.QPost.post;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Request.RequestPostSearchDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements  PostCustomRepository{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RequestPostDto> search(RequestPostSearchDto requestPostSearchDto) {
        return queryFactory.select(Projections.bean(RequestPostDto.class,post.company,
                post.title)).from(post)
                .where(companyEq(requestPostSearchDto), titleEq(requestPostSearchDto))
                .limit(10)
                .orderBy(post.createdAt.asc())
                .fetch();
    }

    private BooleanExpression companyEq(RequestPostSearchDto requestPostSearchDto){
        return requestPostSearchDto.getCompany() != 0 ? post.company.eq(requestPostSearchDto.getCompany()) : null;
    }

    private BooleanExpression titleEq(RequestPostSearchDto requestPostSearchDto){
        return requestPostSearchDto.getTitle() != "" ? post.title.eq(requestPostSearchDto.getTitle()) : null;

    }

}
