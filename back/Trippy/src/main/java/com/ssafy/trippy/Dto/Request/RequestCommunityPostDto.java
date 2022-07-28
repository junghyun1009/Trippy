package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Entity.CommunityPost;
import com.ssafy.trippy.Entity.Location;
import com.ssafy.trippy.Entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestCommunityPostDto {
    private Long id;
    private String title;
    private String description;
    private String memberId;
    private int category;
    private String countryName;
    private String cityName;
    private Double latitude;
    private Double longitude;
    private LocalDateTime meetingTime;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int recruitVolume;
    private int recruitCurrentVolume;
    private int startAge;
    private int endAge;
    private int gender;
    private boolean isLocal;


    // DTO -> ENTITY
    public CommunityPost toEntity() {
        return CommunityPost.builder()
                .id(id)
                .isLocal(isLocal)
                .category(category)
                .description(description)
                .endAge(endAge)
                .endDate(endDate)
                .gender(gender)
                .location(Location.builder().cityName(cityName).countryName(countryName).latitude(latitude).longitude(longitude).build())
                .member(Member.builder().name(memberId).build())
                .meetingTime(meetingTime)
                .recruitVolume(recruitVolume)
                .startAge(startAge)
                .startDate(startDate)
                .recruitCurrentVolume(recruitCurrentVolume)
                .title(title)
                .build();
    }



}
