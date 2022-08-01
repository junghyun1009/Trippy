package com.ssafy.trippy.Dto.Request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestLoginDto {
    String email;
    String password;
}
