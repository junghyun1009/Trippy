package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Entity.Post;
import com.ssafy.trippy.Entity.PostComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestPostCommentDto {
    private Long id;

    private String content;

    private LocalDateTime regDt;


    private Byte isDelete;

    // 대댓글 나중에
    private Long parent;
    private Long rootComment;
    private Long childComment;

    private Post post;

    public PostComment toEntity() {
        return PostComment.builder()
                .id(id)
                .post(Post.builder().id(post.getId()).build())
                .content(content)
                .regDt(regDt)
                .build();

    }

    @Builder
    public RequestPostCommentDto(Long id, String content, LocalDateTime regDt, Post post) {
        this.id = id;
        this.content = content;
        this.regDt = regDt;
        this.post = post;
    }
}
