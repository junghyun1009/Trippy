package com.ssafy.trippy.Dto.Update;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class UpdateCommunityPostDto {
    private String title;
    private String description;
    private int category;

    private Long location_id;
//    private String countryName;
//    private String cityName;
//    private Double latitude;
//    private Double longitude;
    private LocalDateTime meetingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private int gender;
    private boolean isLocal;

    public UpdateCommunityPostDto(String title, String description, int category, Long location_id, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, int gender, boolean isLocal) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.isLocal = isLocal;
        this.location_id = location_id;
    }
}
