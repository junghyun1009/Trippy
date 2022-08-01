package com.ssafy.trippy.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name = "FOLLOW_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "following")
    private Member following;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower")
    private Member follower;

    @Builder
    public Follow(Long id, Member following, Member follower) {
        this.id = id;
        this.following = following;
        this.follower = follower;
    }
}
