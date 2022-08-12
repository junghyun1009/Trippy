package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import com.ssafy.trippy.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    private final MemberService memberService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";


    @PostMapping("/auth/posts")
    public ResponseEntity<?> savePost(HttpServletRequest request, @RequestPart("post") @Valid RequestPostDto requestPostDto, @RequestPart("images") List<MultipartFile> images) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestPostDto.setMember_id(memberId);
        try {
            Long id = postService.savePost(requestPostDto, images);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("저장할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/auth/posts/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") Long post_id) {
        try {
            postService.deletePost(post_id);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("삭제할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/auth/posts/{post_id}")
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long post_id, @RequestBody @Valid RequestPostDto requestPostDto) {
        try {
            postService.updatePost(post_id, requestPostDto);
            return new ResponseEntity<>(SUCCESS, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("수정할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost() {
        List<ResponsePostDto> responsePostDtos = new ArrayList<>();
        try {
            responsePostDtos = postService.findAll();
            return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("게시글이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/detail/{post_id}")
    public ResponseEntity<?> detailPost(@PathVariable("post_id") Long post_id) {
        ResponsePostDto responsePostDto = new ResponsePostDto();
        try {
            responsePostDto = postService.findPostId(post_id);
            return new ResponseEntity<>(responsePostDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/posts/memberDetail")
    public ResponseEntity<?> getAllMemberPost(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponsePostDto> responsePostDtos = postService.findAllByMember(Member.builder().id(memberId).build());
            return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시물이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/{countryName}/{cityName}")
    public ResponseEntity<?> getAllPostByCountryNameAndCityName(@PathVariable("countryName") String countryName,
                                                                @PathVariable("cityName") String cityName) {
        try {
            Location location = Location.builder().countryName(countryName).cityName(cityName).build();
            List<ResponsePostDto> responsePostDtos = postService.findByCity(location);
            return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("해당 게시물을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
