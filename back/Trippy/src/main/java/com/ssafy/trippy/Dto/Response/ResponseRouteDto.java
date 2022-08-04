package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseRouteDto {
    private String routeName;
    private Double lat;
    private Double lng;

    private int index;

    public ResponseRouteDto(Route route){
        this.routeName = route.getRouteName();
        this.lat = route.getLat();
        this.lng = route.getLng();
        this.index = route.getIndex();
    }

    @Builder
    public ResponseRouteDto(String routeName, Double lat, Double lng, int index) {
        this.routeName = routeName;
        this.lat = lat;
        this.lng = lng;
        this.index = index;
    }
}
