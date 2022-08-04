package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.Route;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
public class RequestRouteDto {
    private Long id;
    private String routeName;
    private Double lat;
    private Double lng;
    private int index;
    private Long postId;

    public Route toEntity() {
        return Route.builder().
                id(id)
                .index(index)
                .lat(lat)
                .lng(lng)
                .post(Post.builder().id(postId).build())
                .build();
    }

    @Builder
    public RequestRouteDto(Long id, String routeName, Double lat, Double lng, int index, Long postId) {
        this.id = id;
        this.routeName = routeName;
        this.lat = lat;
        this.lng = lng;
        this.index = index;
        this.postId = postId;
    }
}
