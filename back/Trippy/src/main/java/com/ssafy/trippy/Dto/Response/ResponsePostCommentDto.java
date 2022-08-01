package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Entity.Post;
import com.ssafy.trippy.Entity.PostComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponsePostCommentDto {

    private Long id;

    private String content;

    // memberId

    private Post post;

    private LocalDateTime regDt;

    public ResponsePostCommentDto(PostComment postComment){
        this.id = postComment.getId();
        this.content = postComment.getContent();
        this.post = postComment.getPost();
        this.regDt = postComment.getRegDt();
    }
    @Builder
    public ResponsePostCommentDto(Long id, String content, Post post, LocalDateTime regDt){
        this.id = id;
        this.content = content;
        this.post = post;
        this.regDt = regDt;
    }

}
