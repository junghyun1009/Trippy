package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.*;
import lombok.*;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class RequestPostDto {
    private String title;
    private Byte isDelete;
    private int company;
    private int count;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int representiveImg;
    private Long member_id;
    private List<PostTransport> postTransports;
    private List<RequestDetailLocationDto> detailLocations;

    public Post toEntity() {
        List<DetailLocation> detailLocationList = new ArrayList<>();
        for (RequestDetailLocationDto detailLocation : detailLocations) {
            detailLocationList.add(detailLocation.toEntity());
        }
        return Post.builder()
                .company(company)
                .endDate(endDate)
                .postTransports(postTransports)
                .member(Member.builder().id(member_id).build())
                .count(count)
                .startDate(startDate)
                .detailLocations(detailLocationList)
                .title(title)
                .isDelete(isDelete)
                .representiveImg(representiveImg)
                .build();
    }

    @Builder
    public RequestPostDto(String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, Long memberId, List<PostTransport> postTransports, List<RequestDetailLocationDto> detailLocations) {
        this.title = title;
        this.isDelete = isDelete;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representiveImg = representiveImg;
        this.member_id = member_id;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
    }
}
