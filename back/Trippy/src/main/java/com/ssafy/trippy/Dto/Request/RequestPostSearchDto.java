package com.ssafy.trippy.Dto.Request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestPostSearchDto {

    private String title;
//    private Long transportId;
    private String email;
    private int company;

    @Builder
    public RequestPostSearchDto(String title, String email, int company) {
        this.title = title;
        this.email = email;
        this.company = company;
    }
}
