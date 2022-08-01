package com.ssafy.trippy.Service;


import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;

import java.util.List;

public interface CommunityPostService {
    Long saveCommunityPost(RequestCommunityPostDto requestCommunityPostDto);
    void deleteCommunityPost(RequestCommunityPostDto requestCommunityPostDto);

    void updateCommunityPost(Long id, UpdateCommunityPostDto updateCommunityPostDto);

    List<ResponseCommunityPostDto> getAllCommunityPost();

}
