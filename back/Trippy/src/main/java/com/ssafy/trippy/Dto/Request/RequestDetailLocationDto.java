package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Post;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class RequestDetailLocationDto {

    private String detailLocationName;

    private float rating;

    private String detailLocationContent;

    private String imgPath;

    private Long post_id;

    private Long location_id;

    public DetailLocation toEntity() {
        return DetailLocation.builder()
                .detailLocationContent(detailLocationContent)
                .imgPath(imgPath)
                .post(Post.builder().id(post_id).build())
                .location(Location.builder().id(location_id).build())
                .rating(rating)
                .detailLocationName(detailLocationName)
                .build();
    }

    @Builder
    public RequestDetailLocationDto(String detailLocationName, float rating, String detailLocationContent, String imgPath, Long post_id, Long location_id) {
        this.detailLocationName = detailLocationName;
        this.rating = rating;
        this.detailLocationContent = detailLocationContent;
        this.imgPath = imgPath;
        this.post_id = post_id;
        this.location_id = location_id;
    }
}
