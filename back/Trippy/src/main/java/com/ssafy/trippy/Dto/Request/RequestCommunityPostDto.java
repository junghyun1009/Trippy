package com.ssafy.trippy.Dto.Request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RequestCommunityPostDto {
    private Long id;

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;
    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String description;
    private Long member_id;
    @NotNull(message = "동행타입은 필수 입력 값입니다.")
    private int category;
    private Long locationId;
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

    @NotNull(message = "최대인원은 필수 입력 값입니다.")
    private int recruitVolume;

    @NotNull(message = "현재인원은 필수 입력 값입니다.")
    private int recruitCurrentVolume;

    @NotNull(message = "최소나이는 필수 입력 값입니다.")
    private int startAge;

    @NotNull(message = "최대나이는 필수 입력 값입니다.")
    private int endAge;

    private String gender;

    private boolean Local;


    private String place;

    private boolean Day;


    // DTO -> ENTITY
    public CommunityPost toEntity() {
        return CommunityPost.builder()
                .id(id)
                .Day(Day)
                .Local(Local)
                .category(category)
                .description(description)
                .endAge(endAge)
                .endDate(endDate)
                .gender(gender)
                .location(Location.builder().id(locationId).cityName(cityName).countryName(countryName).build())
                .member(Member.builder().id(member_id).build())
                .meetingTime(meetingTime)
                .recruitVolume(recruitVolume)
                .startAge(startAge)
                .startDate(startDate)
                .recruitCurrentVolume(recruitCurrentVolume)
                .title(title)
                .place(place)
                .build();
    }



}
