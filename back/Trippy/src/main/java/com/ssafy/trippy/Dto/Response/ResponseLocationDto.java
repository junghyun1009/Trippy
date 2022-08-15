package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.Location;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseLocationDto {
    private String countryName;
    private String cityName;

    @Builder
    public ResponseLocationDto (Location location){
        this.countryName = location.getCountryName();
        this.cityName = location.getCityName();
    }
}
