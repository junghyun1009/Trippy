package com.ssafy.trippy.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Double lat;
    private Double lng;

    private int index;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="POST_ID")
    private Post post;

    @Builder
    public Route(Long id, String routeName, Double lat, Double lng, Post post, int index) {
        this.id = id;
        this.routeName = routeName;
        this.lat = lat;
        this.lng = lng;
        this.post = post;
        this.index = index;
    }

    public void update(String routeName, Double lat, Double lng, int index) {
        this.routeName = routeName;
        this.lat = lat;
        this.lng = lng;
        this.index =index;
    }
}

