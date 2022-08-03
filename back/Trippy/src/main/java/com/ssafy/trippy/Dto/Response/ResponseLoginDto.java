package com.ssafy.trippy.Dto.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseLoginDto {
    private String accessToken;
    private String refreshToken;
}
