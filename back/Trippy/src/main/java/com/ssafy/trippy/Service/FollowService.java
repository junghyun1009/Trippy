package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Entity.Follow;
import com.ssafy.trippy.Entity.Member;

import java.util.List;

public interface FollowService {
    void follow(long followingId, long followerId);
    void unfollow(long followingId, long followerId);
    List<ResponseFollowDto> getFollowerList(Long memberId, Long followerId);
    List<ResponseFollowDto> getFollowingList(Long memberId, Long followingId);

}
