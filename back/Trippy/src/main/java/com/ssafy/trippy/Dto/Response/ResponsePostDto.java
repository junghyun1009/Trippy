package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class ResponsePostDto {
    private Long id;
    private String title;
    private int company;
    private int count;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int representiveImg;
    private String email;
    private List<ResponseTransport> transportName;
    private List<ResponseDetailLocationDto> detailLocations;

    private List<ResponsePostCommentDto> comments;
    private List<ResponseRouteDto> routes;


    @Builder
    public ResponsePostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.company = post.getCompany();
        this.count = post.getCount();
        this.startDate = post.getStartDate();
        this.endDate = post.getEndDate();
        this.representiveImg = post.getRepresentiveImg();
        this.email = post.getMember().getEmail();
        this.transportName = Converter.convertTransportList(Converter.convertTransportsToPostTransports(post.getPostTransports()));
        this.detailLocations = Converter.convertDetailLocationList(post.getDetailLocations());
        this.comments = Converter.convertPostCommentList(post.getPostComments());
        this.routes = Converter.convertRouteList(post.getRoutes());
    }
}
