package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ResponseImageDto uploadImage(MultipartFile file,Long detailLocationId);

    void deleteImage(String destinationFileName);
}
