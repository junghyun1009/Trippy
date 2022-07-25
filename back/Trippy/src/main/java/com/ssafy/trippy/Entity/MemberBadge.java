package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class MemberBadge {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_BADGE_ID")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="멤버 뱃지 이름은 필수입니다.")
    private String name;

    @Column(nullable = false)
    @NotBlank(message="멤버 뱃지 설명은 필수입니다.")
    private String desc;

    @Column(nullable = false)
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "MENBER_ID")
    private Member member;

    @Column(nullable = false)
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BADGE_ID")
    private Badge badge;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotBlank(message="생성일은 필수입니다.")
    private Timestamp createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @NotBlank(message="수정일은 필수입니다.")
    private Timestamp updatedAt;
}
