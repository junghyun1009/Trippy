package com.ssafy.trippy.Dto.Response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class ResponseSavepostDto {

    Long postId;

    List<ResponseBadgeDto> badgeDtos = new ArrayList<>();

    public ResponseSavepostDto(Long postId){
        this.postId = postId;
    }

    public void addBadge(ResponseBadgeDto responseBadgeDto){
        badgeDtos.add(responseBadgeDto);
    }
}
