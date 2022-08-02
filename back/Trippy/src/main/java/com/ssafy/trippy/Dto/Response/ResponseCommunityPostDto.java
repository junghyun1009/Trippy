package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.CommunityPost;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ResponseCommunityPostDto {
    private String title;
    private String description;
    private int category;
    private String countryName;
    private String cityName;
    private LocalDateTime meetingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private int gender;
    private boolean isLocal;


    public ResponseCommunityPostDto(CommunityPost communityPost) {
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
    }

    @Builder
    public ResponseCommunityPostDto(String title, String description, int category, String countryName, String cityName, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, int gender, boolean isLocal) {
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
    }
}
