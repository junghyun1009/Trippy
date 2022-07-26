package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="BOOKMARK_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="COMMUNITY_POST_ID")
//    private Post post;

    @Column(nullable = false, length=15)
    @NotBlank(message="생성일자는 필수값입니다.")
    private LocalDateTime createAt;

    @Column(nullable = false, length=15)
    @NotBlank(message="수정날짜는 필수값입니다.")
    private LocalDateTime updateAt;


    public void setMember(Member member) {
        this.member = member;
        member.getBookmarks().add(this);
    }

    public Bookmark createBookmark(Member member) {
        Bookmark bookmark = new Bookmark();
        bookmark.setMember(member);
        return bookmark;
    }

}
