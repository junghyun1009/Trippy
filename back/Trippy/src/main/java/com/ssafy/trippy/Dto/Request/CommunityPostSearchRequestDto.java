package com.ssafy.trippy.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommunityPostSearchRequestDto {

    private String countryName;
    private String cityName;

    @Builder
    public CommunityPostSearchRequestDto(String countryName, String cityName) {
        this.countryName = countryName;
        this.cityName = cityName;
    }
}
