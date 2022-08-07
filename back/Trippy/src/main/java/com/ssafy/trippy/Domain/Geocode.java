//package com.ssafy.trippy.Domain;
//
//import lombok.AccessLevel;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//
//@Entity
//@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//public class Geocode {
//    @Id
//    @GeneratedValue
//    @Column(name="geocode_id")
//    private Long id;
//
//    private Double lat;
//    private Double lng;
//
//    @OneToOne(mappedBy="geocode")
//    private Route route;
//
//    @Builder
//    public Geocode(Long id, Double lat, Double lng, Route route) {
//        this.id = id;
//        this.lat = lat;
//        this.lng = lng;
//        this.route = route;
//    }
//
//    public void use(Route route){
//        this.route = route;
//    }
//}
