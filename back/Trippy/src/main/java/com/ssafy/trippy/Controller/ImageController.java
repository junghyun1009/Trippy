package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Response.ResponseImageDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Service.ImageService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    private final MemberService memberService;

    @PostMapping("/upload")
    public ResponseImageDto upload(@RequestPart(value = "file") MultipartFile file){
        System.out.println(file);
        return imageService.uploadImage(file,null);
    }
    @PostMapping("/upload/member/{member_id}")
    public ResponseEntity<?> updateUserProfile(@RequestBody MultipartFile file, @PathVariable("member_id") Long memberId)  {
        System.out.println(file);
        ResponseImageDto responseImageDto = imageService.uploadImage(file,null);
        if(responseImageDto.getFileName()==null) {
            return new ResponseEntity<>("이미지가 없습니다.", HttpStatus.BAD_REQUEST);
        }
        ResponseMemberDto responseMemberDto = memberService.selectMember(memberId);
        UpdateMemberDto updateMemberDto = new UpdateMemberDto(responseMemberDto.getName(), responseMemberDto.getEmail(),
                responseMemberDto.getPhone(), responseMemberDto.getGender(),responseMemberDto.getBirth(),responseImageDto.getFileName(),responseMemberDto.getDesc());
        memberService.updateMember(memberId, updateMemberDto);
        return new ResponseEntity<>(responseImageDto,HttpStatus.OK);
    }

}
