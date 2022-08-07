package com.ssafy.trippy.Dto.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ResponsePostSearchDto {
    private String email;
    private String title;
    private LocalDateTime startDate;

    @Builder
    public ResponsePostSearchDto(String email, String title, LocalDateTime startDate) {
        this.email = email;
        this.title = title;
        this.startDate = startDate;
    }
}
