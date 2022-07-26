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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMMUNITY_POST_ID")
    private Post post;


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
