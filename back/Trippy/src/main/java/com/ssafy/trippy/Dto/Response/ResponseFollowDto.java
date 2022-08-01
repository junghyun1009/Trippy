package com.ssafy.trippy.Dto.Response;


import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Domain.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseFollowDto {
    private Long id;

    private Member following;

    private Member follower;

    public ResponseFollowDto(Follow follow){
        this.id = follow.getId();
        this.following = follow.getFollowing();
        this.follower = follow.getFollower();
    }
    @Builder
    public ResponseFollowDto(Long id, Member following, Member follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }
}
