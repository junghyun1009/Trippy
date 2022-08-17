package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Converter.Converter;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePostCommentDto implements Serializable {

    private Long id;
    private String content;
    private Long postId;
    private String name;
    private String imgPath;
    private Long memberId;
    private Long parentId;

    private List<ResponsePostCommentDto> children = new ArrayList<>();

    @Builder
    public ResponsePostCommentDto(PostComment postComment) {
        this.id = postComment.getId();
        this.content = postComment.getContent();
        this.postId = postComment.getPost().getId();
        this.parentId = postComment.getParent()== null ? 0 : postComment.getParent().getId();
        this.memberId = postComment.getMember().getId();
        this.name = postComment.getName();
        this.imgPath = postComment.getImgPath();
    }

    public ResponsePostCommentDto(Long id, String content, Long postId,Long memberId,String name, String imgPath) {
        this.id = id;
        this.content = content;
        this.postId = postId;
        this.memberId = memberId;
        this.name = name;
        this.imgPath = imgPath;
    }


    public static ResponsePostCommentDto convertCommentToDto(PostComment postComment){
        return new ResponsePostCommentDto(postComment.getId(),postComment.getContent(), postComment.getPost().getId(), postComment.getMember().getId(), postComment.getName(), postComment.getImgPath());

    }

}
