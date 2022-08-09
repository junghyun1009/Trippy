package com.ssafy.trippy.Service;

import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    ResponseImageDto uploadImage(MultipartFile file,Long detailLocationId, String path);

    void deleteImage(String destinationFileName);

    ResponseImageDto getImageById(Long id);

    List<Resource> getImagesByPostId(Long postId);
}
