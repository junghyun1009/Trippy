package com.ssafy.trippy.Dto.Response;

//import com.ssafy.trippy.Domain.Geocode;
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
    private int index;

    private Double lng;
    private Double lat;

//    private Geocode geocode;

    public ResponseRouteDto(Route route){
        this.routeName = route.getRouteName();
        this.index = route.getIndex();
        this.lng = route.getLng();
        this.lat = route.getLat();
//        this.geocode = route.getGeocode();
    }

    @Builder
    public ResponseRouteDto(String routeName,int index, Double lat, Double lng) {
        this.routeName = routeName;
        this.index = index;
        this.lat = lat;
        this.lng = lng;
//        this.geocode = geocode;
    }
}
