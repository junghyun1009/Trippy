package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;

import java.util.List;

public interface FollowService {
    ResponseFollowDto follow(RequestFollowDto requestFollowDto);
    void unfollow(RequestFollowDto requestFollowDto);
    List<ResponseFollowDto> getFollowers(Long memberId);
    List<ResponseFollowDto> getFollowings(Long memberId);
    Long getFollowersCnt(Long memberId);
    Long getFollowingsCnt(Long memberId);
}
