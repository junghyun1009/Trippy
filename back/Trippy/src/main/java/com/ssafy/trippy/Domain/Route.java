package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Route {
    @Id
    @GeneratedValue
    @Column(name="route_id")
    private Long id;
    private String routeName;
    private int index;
    private Double lat;
    private Double lng;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;
;

    @Builder
    public Route(Long id, String routeName, int index, Post post, Double lat, Double lng) {
        this.id = id;
        this.routeName = routeName;
        this.index = index;
        this.post = post;
        this.lat = lat;
        this.lng = lng;
    }

    public void update(String routeName, int index, Double lat, Double lng) {
        this.routeName = routeName;
        this.index =index;
        this.lat = lat;
        this.lng = lng;
    }

    public void setRoutePost(Post post){
        this.post = post;
        post.getRoutes().add(this);
    }
//    public void setGeocode(Geocode geocode){
//        this.geocode = geocode;
//        geocode.use(this);
//    }
}

