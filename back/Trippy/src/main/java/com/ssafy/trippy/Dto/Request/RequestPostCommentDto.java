package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPostCommentDto {

    private Long id;
    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String content;
    private String name;
    private String imgPath;
    private Long memberId;

    private Long postId;

    private Long parentId;
    private List<PostComment> children = new ArrayList<>();

    @Builder
    public RequestPostCommentDto(Long id, String content, Long memberId, String name, String imgPath) {
        this.id = id;
        this.content = content;
        this.memberId = memberId;
        this.name = name;
        this.imgPath = imgPath;

    }

    public PostComment toEntity(){
        return PostComment.builder()
                .id(id)
                .content(content)
                .name(name)
                .imgPath(imgPath)
                .member(Member.builder().id(memberId).build())
                .children(children)
                .build();
    }


}
