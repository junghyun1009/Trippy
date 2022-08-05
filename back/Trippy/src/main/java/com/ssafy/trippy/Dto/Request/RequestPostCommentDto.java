package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostComment;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestPostCommentDto implements Serializable {

    private Long id;
    private String content;
    private LocalDateTime regDt;
    private Long memberId;
    private String email;
    private List<RequestPostCommentDto> children = new ArrayList<>();

    public RequestPostCommentDto(Long id, String content, Long memberId, String email) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.email = email;

    }

    public static RequestPostCommentDto convertCommentToDto(PostComment postComment){
        return new RequestPostCommentDto(postComment.getId(), postComment.getContent(), postComment.getMember().getId(),
                postComment.getMember().getEmail());

    }




}
