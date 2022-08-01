package com.ssafy.trippy.Dto.Update;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.PostTransport;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdatePostDto {
    private String title;
    private Byte isDelete;
    private int company;
    private int count;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int representiveImg;
    private List<PostTransport> postTransports;
    private List<DetailLocation> detailLocations;

    public UpdatePostDto(String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, List<PostTransport> postTransports, List<DetailLocation> detailLocations) {
        this.title = title;
        this.isDelete = isDelete;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representiveImg = representiveImg;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
    }
}
