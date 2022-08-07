package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Service.ImageService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.sun.corba.se.impl.util.Version.asString;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    private final MemberService memberService;

//  추후에 업로드 형식에 따라 param받는 형식 고려(현재는 postman이 requestpart에 호환)
    @PostMapping("/upload")
    public ResponseImageDto upload(@RequestPart(value = "file") MultipartFile file){
        return imageService.uploadImage(file,null);
    }
    @PostMapping("/upload/member/{member_id}")
    public ResponseEntity<?> updateUserProfile(@RequestPart(value = "file") MultipartFile file, @PathVariable("member_id") Long memberId)  {
        ResponseImageDto responseImageDto = imageService.uploadImage(file,null);
        if(responseImageDto.getFileName()==null) {
            return new ResponseEntity<>("이미지가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        ResponseMemberDto responseMemberDto = memberService.selectMember(memberId);
        UpdateMemberDto updateMemberDto = new UpdateMemberDto(responseMemberDto.getName(), responseMemberDto.getEmail(),
                responseMemberDto.getPhone(), responseMemberDto.getGender(),responseMemberDto.getBirth(),responseImageDto.getFileName(),responseMemberDto.getDesc());
        memberService.updateMember(memberId, updateMemberDto);
        imageService.deleteImage(responseMemberDto.getImg_path());
        return new ResponseEntity<>(responseImageDto,HttpStatus.OK);
    }

    @PostMapping("/upload/post/{detail_location_id}")
    public ResponseEntity<?> updatePostProfile(@RequestPart(value = "file") MultipartFile file, @PathVariable("detail_location_id") Long detailLocId){
            ResponseImageDto responseImageDto = imageService.uploadImage(file,detailLocId);
            if(responseImageDto.getFileName()==null) {
                return new ResponseEntity<>("이미지가 없습니다.", HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(responseImageDto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{image_id}")
    public ResponseEntity<?> deleteImage(@PathVariable("image_id") Long imageId) {
        imageService.deleteImage(imageService.getImageById(imageId).getFileName());
        return new ResponseEntity<>("success",HttpStatus.OK);
    }

    @GetMapping("/{post_id}")
    public ResponseEntity<?> getImageByPostId(@PathVariable("post_id") Long postId) {
        List<Resource> resources =  imageService.getImagesByPostId(postId);
        List<File> files = new ArrayList<>();
        for (Resource resource: resources){
            try {
                files.add(resource.getFile());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(files, HttpStatus.OK);
    }

}
