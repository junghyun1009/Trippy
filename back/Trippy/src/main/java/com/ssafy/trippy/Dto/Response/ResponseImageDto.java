package com.ssafy.trippy.Dto.Response;

import com.ssafy.trippy.Domain.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Getter
@NoArgsConstructor
public class ResponseImageDto {

    private String fileName;

    private String fileUrl;

    @Builder
    public ResponseImageDto(String fileName, String fileUrl) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
    }

    public ResponseImageDto(Image image){
        this.fileName = image.getFileName();
        this.fileUrl = image.getFileUrl();
    }
}
