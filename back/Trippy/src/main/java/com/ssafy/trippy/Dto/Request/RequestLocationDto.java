package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Location;
import lombok.Builder;

public class RequestLocationDto {
    private Long id;

    private String countryName;

    private String cityName;

    private Double latitude;

    private Double longitude;

    public Location toEntity() {
        return Location.builder()
                .id(id)
                .cityName(cityName)
                .countryName(countryName)
                .latitude(latitude)
                .longitude(longitude)
                .build();
    }

    @Builder
    public RequestLocationDto(String countryName, String cityName, Double latitude, Double longitude) {
        this.countryName = countryName;
        this.cityName = cityName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
