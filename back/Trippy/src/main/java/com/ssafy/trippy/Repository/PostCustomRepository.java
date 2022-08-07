package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Request.RequestPostSearchDto;

import java.util.List;

public interface PostCustomRepository {
    List<RequestPostDto> search(RequestPostSearchDto requestPostSearchDto);
}
