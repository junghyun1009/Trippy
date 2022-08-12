package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Post;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestDetailLocationDto {

    private String detailLocationName;

    private float rating;

    private String detailLocationContent;

    private Long post_id;

    private Long location_id;

    public DetailLocation toEntity() {
        return DetailLocation.builder()
                .detailLocationContent(detailLocationContent)
                .post(Post.builder().id(post_id).build())
                .location(Location.builder().id(location_id).build())
                .rating(rating)
                .detailLocationName(detailLocationName)
                .build();
    }

    @Builder
    public RequestDetailLocationDto(String detailLocationName, float rating, String detailLocationContent, Long post_id, Long location_id) {
        this.detailLocationName = detailLocationName;
        this.rating = rating;
        this.detailLocationContent = detailLocationContent;
        this.post_id = post_id;
        this.location_id = location_id;
    }
}
