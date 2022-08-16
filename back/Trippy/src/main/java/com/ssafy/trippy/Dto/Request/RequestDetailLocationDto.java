package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Post;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestDetailLocationDto {

    @NotBlank(message="상세지역명은 필수값입니다.")
    private String detailLocationName;

    @NotNull(message="상세지역명은 필수값입니다.")
    private float rating;

    @NotBlank(message="상세지역명은 필수값입니다.")
    private String detailLocationContent;

    private String filename;

    private Long post_id;

    public DetailLocation toEntity() {
        return DetailLocation.builder()
                .detailLocationContent(detailLocationContent)
                .post(Post.builder().id(post_id).build())
                .rating(rating)
                .detailLocationName(detailLocationName)
                .filename(filename)
                .build();
    }

    @Builder
    public RequestDetailLocationDto(String detailLocationName, float rating, String detailLocationContent, Long post_id,String filename) {
        this.detailLocationName = detailLocationName;
        this.rating = rating;
        this.detailLocationContent = detailLocationContent;
        this.post_id = post_id;
        this.filename = filename;
    }
}
