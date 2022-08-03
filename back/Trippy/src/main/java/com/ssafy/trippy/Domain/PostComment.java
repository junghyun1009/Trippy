package com.ssafy.trippy.Domain;

import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class PostComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long id;

    private String content;

    private LocalDateTime regDt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private PostComment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<PostComment> children = new ArrayList<>();

    public static PostComment createComment(String content, Post post, Member member, PostComment parent){
        PostComment postComment = new PostComment();
        postComment.content = content;
        postComment.post = post;
        postComment.member = member;
        postComment.content = content;
        postComment.parent = parent;
        return postComment;
    }


}
