package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="BADGE_ID")
    private Badge badge;

    @Column(nullable = false, length=15)
    @NotBlank(message="생성일자는 필수값입니다.")
    private LocalDateTime createAt;

    @Column(nullable = false, length=15)
    @NotBlank(message="수정날짜는 필수값입니다.")
    private LocalDateTime updateAt;
}
