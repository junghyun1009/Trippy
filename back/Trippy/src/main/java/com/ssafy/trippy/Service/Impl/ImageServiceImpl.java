package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Image;
import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Repository.DetailLocationRepository;
import com.ssafy.trippy.Repository.ImageRespository;
import com.ssafy.trippy.Service.ImageService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImageServiceImpl implements ImageService {

    private final ImageRespository imageRespository;

    private final DetailLocationRepository detailLocationRepository;

    private String fileUrl = "C:\\Users\\0312j\\OneDrive\\바탕 화면\\SSAFY 7기\\2학기\\공통PJT\\S07P12A506\\back\\Trippy\\src\\main\\webapp\\static\\image\\";

    @Override
    public ResponseImageDto uploadImage(MultipartFile file, Long detailLocationId)  {
        try {
            String sourceFileName = file.getOriginalFilename();
            String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
            String destinationFileName = RandomStringUtils.randomAlphabetic(32) + "." + sourceFileNameExtension;
            File destinationFile = new File(fileUrl + destinationFileName);
            destinationFile.getParentFile().mkdirs();
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

    @Override
    public void deleteImage(String destinationFileName){
        File file = new File(fileUrl + destinationFileName);
        if(file.exists()) {
            file.delete();
            imageRespository.deleteByFileName(destinationFileName);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseImageDto getImageById(Long id){
        return new ResponseImageDto(imageRespository.findById(id).get());
    }


    @Override
    public List<Resource> getImagesByPostId(Long postId) {
        List<DetailLocation> detailLocations = detailLocationRepository.findAllByPostId(postId);
        List<Image> images = new ArrayList<>();
        for (DetailLocation detailLocation: detailLocations){
            List<Image> addimages = imageRespository.findAllByDetailLocationId(detailLocation.getId());
            for (Image image:addimages){
                images.add(image);
            }
        }
        List<Resource> resources = new ArrayList<>();
        for(Image image:images){
            try {
                FileSystemResource resource = new FileSystemResource(fileUrl+image.getFileName());
                if (!resource.exists()) {
                    throw new IllegalArgumentException("이미지가 없습니다.");
                }
                HttpHeaders header = new HttpHeaders();
                Path filePath = null;
                filePath = Paths.get(fileUrl+image.getFileName());
                header.add("Content-Type", Files.probeContentType(filePath));
                resources.add(resource);
            } catch (Exception e) {
                throw new IllegalArgumentException("error");
            }
        }
        return resources;
    }

}
