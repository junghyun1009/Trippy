package com.ssafy.trippy.Domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class PostComment extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="COMMENT_ID")
    private Long id;

    private String content;

    private LocalDateTime regDt;

    private String name;
    private String imgPath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parent_id")
    private PostComment parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<PostComment> children = new ArrayList<>();

    public static PostComment createComment(String content, Post post, Member member, PostComment parent, String name, String imgPath){
        PostComment postComment = new PostComment();
        postComment.post = post;
        postComment.member = member;
        postComment.content = content;
        postComment.parent = parent;
        postComment.imgPath = imgPath;
        postComment.name = name;
        return postComment;
    }

    @Builder
    public PostComment(String content, String name, String imgPath) {
        this.content = content;
        this.name = name;
        this.imgPath = imgPath;
    }

    public void update(String content) {
        this.content = content;
    }


}
