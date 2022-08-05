package com.ssafy.trippy.Dto.Response;

import com.querydsl.core.annotations.QueryProjection;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class  ResponsePostCommentDto {

    private String content;
    private String email;
    private LocalDateTime regDt;
    private Long postId;
    private Long memberId;
    private Long parentId;

    @Builder
    public ResponsePostCommentDto(PostComment postComment) {
        this.content = postComment.getContent();
        this.email = postComment.getMember().getEmail();
        this.postId = postComment.getPost().getId();
        this.parentId = postComment.getParent()== null ? 0 : postComment.getParent().getId();
        this.memberId = postComment.getMember().getId();
        this.regDt = postComment.getRegDt();
    }
}
