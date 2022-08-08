package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import com.ssafy.trippy.Service.DetailLocationService;
import com.ssafy.trippy.Service.MemberService;
import com.ssafy.trippy.Service.PostService;
import com.ssafy.trippy.Service.RouteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> savePost(HttpServletRequest request, @RequestBody @Valid RequestPostDto requestPostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestPostDto.setMember_id(memberId);
        try {
            postService.savePost(requestPostDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/auth/posts/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") Long post_id) {
        try {
            postService.deletePost(post_id);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/auth/posts/{post_id}")
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long post_id, @RequestBody @Valid RequestPostDto requestPostDto) {
        try {
            postService.updatePost(post_id, requestPostDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost() {
        List<ResponsePostDto> responsePostDtos = new ArrayList<>();
        try {
            responsePostDtos = postService.findAll();

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<List<ResponsePostDto>>(responsePostDtos, HttpStatus.OK);
    }

    @GetMapping("/posts/detail/{post_id}")
    public ResponseEntity<?> detailPost(@PathVariable("post_id") Long post_id) {
        ResponsePostDto responsePostDto = new ResponsePostDto();
            try {
                responsePostDto = postService.findPostId(post_id);

            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<ResponsePostDto>(responsePostDto, HttpStatus.OK);
    }

    @GetMapping("/auth/posts/memberDetail")
    public ResponseEntity<?> getAllMemberPost(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponsePostDto> responsePostDtos = postService.findAllByMember(Member.builder().id(memberId).build());
            return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
    }


}
