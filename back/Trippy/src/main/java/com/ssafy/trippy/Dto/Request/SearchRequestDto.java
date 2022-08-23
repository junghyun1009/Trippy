package com.ssafy.trippy.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequestDto {

    private String title;
    private int company;
    private Long transportId;

    private String countryName;
    private String cityName;

    @Builder
    public SearchRequestDto(String title, int company, Long transportId,String countryName,String cityName) {
        this.title = title;
        this.company = company;
        this.transportId = transportId;
        this.countryName = countryName;
        this.cityName = cityName;
    }
}
