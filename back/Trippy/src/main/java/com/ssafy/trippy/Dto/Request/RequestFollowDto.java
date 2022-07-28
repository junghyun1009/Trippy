package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Entity.Follow;
import com.ssafy.trippy.Entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
public class RequestFollowDto {
    private Long id;

    private Member following;

    private Member follower;

    public Follow toEntity() {
        return Follow.builder()
                .id(id)
                .follower(follower)
                .following(following)
                .build();
    }

}
