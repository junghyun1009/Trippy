package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Image;
import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Repository.ImageRespository;
import com.ssafy.trippy.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRespository imageRespository;

    private String fileUrl = "C:\\Users\\0312j\\OneDrive\\바탕 화면\\SSAFY 7기\\2학기\\공통PJT\\S07P12A506\\back\\Trippy\\src\\main\\webapp\\static\\image\\";
    public ResponseImageDto uploadImage(MultipartFile file, Long detailLocationId)  {
        try {
            String sourceFileName = file.getOriginalFilename();
            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
            String destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + sourceFileNameExtension;
            File destinationFile = new File(fileUrl + destinationFileName);
            destinationFile.getParentFile().mkdirs();
            System.out.println(destinationFileName);
            file.transferTo(destinationFile);

            Image image = new Image(destinationFileName, sourceFileName, fileUrl, detailLocationId);
            if(detailLocationId!=null) imageRespository.save(image);
            return ResponseImageDto.builder()
                    .fileName(destinationFileName)
                    .fileOriName(sourceFileName)
                    .fileUrl(fileUrl)
                    .detailLocationId(detailLocationId)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseImageDto.builder().build();
        }
    }

    public void deleteImage(String destinationFileName){
        File file = new File(fileUrl + destinationFileName);
        if(file.exists()) {
            file.delete();
            imageRespository.deleteByFileName(destinationFileName);
        }
    }

}
