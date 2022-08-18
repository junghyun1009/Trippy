package com.ssafy.trippy.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.trippy.Domain.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestPostDto {
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    private String countryName;
    private String cityName;
    private int company;
    private int count;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private Long member_id;
    private List<RequestPostTransPortDto> postTransports;
    private List<RequestDetailLocationDto> detailLocations;
    private Long location_id;
    private List<RequestRouteDto> routes;

    public Post toEntity() {
        List<DetailLocation> detailLocationList = new ArrayList<>();
        for (RequestDetailLocationDto detailLocation : detailLocations) {
            detailLocationList.add(detailLocation.toEntity());
        }
        List<Route> routeList = new ArrayList<>();
        for (RequestRouteDto requestRouteDto:routes){
            routeList.add(requestRouteDto.toEntity());
        }
        List<PostTransport> postTransportList = new ArrayList<>();
        for(RequestPostTransPortDto requestPostTransPortDto:postTransports){
            postTransportList.add(requestPostTransPortDto.toEntity());
        }
        return Post.builder()
                .company(company)
                .endDate(endDate)
                .postTransports(postTransportList)
                .member(Member.builder().id(member_id).build())
                .count(count)
                .startDate(startDate)
                .detailLocations(detailLocationList)
                .title(title)
                .routes(routeList)
                .location(Location.builder().id(location_id).build())
                .build();
    }




    @Builder
    public RequestPostDto(String title, int company, int count, LocalDateTime startDate, LocalDateTime endDate, List<RequestPostTransPortDto> postTransports, List<RequestDetailLocationDto> detailLocations, List<RequestRouteDto> routes,Long member_id) {
        this.title = title;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.member_id = member_id;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
        this.routes = routes;
    }
}
