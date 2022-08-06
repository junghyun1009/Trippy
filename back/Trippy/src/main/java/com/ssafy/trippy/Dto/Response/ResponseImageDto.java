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

    private String fileOriName;

    private String fileUrl;

    private Long detailLocationId;

    @Builder
    public ResponseImageDto(String fileName, String fileOriName, String fileUrl, Long detailLocationId) {
        this.fileName = fileName;
        this.fileOriName = fileOriName;
        this.fileUrl = fileUrl;
        this.detailLocationId = detailLocationId;
    }

    public ResponseImageDto(Image image){
        this.fileName = image.getFileName();
        this.fileOriName = image.getFileOriName();
        this.fileUrl = image.getFileUrl();
        this.detailLocationId = image.getDetailLocationId();
    }
}
