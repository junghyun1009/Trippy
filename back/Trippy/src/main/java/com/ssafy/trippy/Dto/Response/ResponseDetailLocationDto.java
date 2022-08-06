package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.DetailLocation;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class ResponseDetailLocationDto {
    private String detailLocationName;

    private float rating;

    private String detailLocationContent;

    private String imgPath;

    @Builder
    public ResponseDetailLocationDto(DetailLocation detailLocation) {
        this.detailLocationName = detailLocation.getDetailLocationName();
        this.rating = detailLocation.getRating();
        this.detailLocationContent =detailLocation.getDetailLocationContent();
        this.imgPath = detailLocation.getImgPath();
    }
}
