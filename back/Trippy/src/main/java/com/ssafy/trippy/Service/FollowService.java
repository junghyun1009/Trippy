package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;

import java.util.List;

public interface FollowService {
    ResponseFollowDto follow(RequestFollowDto requestFollowDto);
    void unfollow(RequestFollowDto requestFollowDto);
    List<ResponseFollowDto> getFollowerList(Long memberId);
//    List<ResponseFollowDto> getFollowingList(Long memberId, Long followingId);
}
