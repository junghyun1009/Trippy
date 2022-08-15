package com.ssafy.trippy.Domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MemberBadge extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name="MEMBER_BADGE_ID")
    private Long id;

    private String name;

    private String description;

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "MENBER_ID")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BADGE_ID")
    private Badge badge;

    public MemberBadge(Member member, Badge badge) {
        this.member = member;
        this.badge = badge;
    }
}
