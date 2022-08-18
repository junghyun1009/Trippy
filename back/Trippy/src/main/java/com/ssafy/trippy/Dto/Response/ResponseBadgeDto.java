package com.ssafy.trippy.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseBadgeDto {

    String name;

    public ResponseBadgeDto(String name){
        this.name = name;
    }
}
