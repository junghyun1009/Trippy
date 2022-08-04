package com.ssafy.trippy.Service;


import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;

import java.util.List;

public interface PostService {

    List<ResponsePostDto> findAll();
    List<ResponsePostDto> findAllByMember(Member member);

    void savePost(RequestPostDto requestPostDto);

    void deletePost(Long id);

    void updatePost(Long id, RequestPostDto requestPostDto);

    ResponsePostDto findPostId(Long id);



}
