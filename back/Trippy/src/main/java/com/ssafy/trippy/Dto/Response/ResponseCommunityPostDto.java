package com.ssafy.trippy.Dto.Response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.trippy.Domain.CommunityPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseCommunityPostDto {
    private Long id;
    private String title;
    private String description;
    private int category;
    private String countryName;
    private String cityName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime meetingTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private String gender;
    private boolean isLocal;
    private String place;
    private boolean isDAY;


    public ResponseCommunityPostDto(CommunityPost communityPost) {
        this.id = communityPost.getId();
        this.title = communityPost.getTitle();
        this.description = communityPost.getDescription();
        this.category = communityPost.getCategory();
        this.countryName = communityPost.getLocation().getCountryName();
        this.cityName = communityPost.getLocation().getCityName();
        this.meetingTime = communityPost.getMeetingTime();
        this.startDate = communityPost.getStartDate();
        this.endDate = communityPost.getEndDate();
        this.recruitVolume = communityPost.getRecruitVolume();
        this.recruitCurrentVolume = communityPost.getRecruitCurrentVolume();
        this.startAge = communityPost.getStartAge();
        this.endAge = communityPost.getEndAge();
        this.gender = communityPost.getGender();
        this.isLocal = communityPost.isLocal();
        this.place = communityPost.getPlace();
        this.isDAY = communityPost.isDAY();
    }

    @Builder
    public ResponseCommunityPostDto(Long id, String title, String description, int category, String countryName, String cityName, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, String gender, boolean isLocal, String place, boolean isDAY) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.countryName = countryName;
        this.cityName = cityName;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.isLocal = isLocal;
        this.place = place;
        this.isDAY = isDAY;
    }
}
