package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.DetailLocation;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestDetailLocationDto;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import com.ssafy.trippy.Service.DetailLocationService;
import com.ssafy.trippy.Service.MemberService;
import com.ssafy.trippy.Service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final DetailLocationService detailLocationService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";


    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody @Valid RequestPostDto requestPostDto) {
        postService.savePost(requestPostDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{post_id}")
    public ResponseEntity<?> deletePost(@PathVariable("post_id") Long post_id) {
        postService.deletePost(post_id);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/{post_id}")
    public ResponseEntity<?> updatePost(@PathVariable("post_id") Long post_id, @RequestBody @Valid RequestPostDto requestPostDto){
        postService.updatePost(post_id, requestPostDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllPost() {
        List<ResponsePostDto> responsePostDtos = postService.findAll();
        return new ResponseEntity<List<ResponsePostDto>>(responsePostDtos, HttpStatus.OK);
    }

    @GetMapping("/detail/{post_id}")
    public ResponseEntity<?> detailPost(@PathVariable("post_id") Long post_id){
        ResponsePostDto responsePostDto = postService.findPostId(post_id);
        return new ResponseEntity<ResponsePostDto>(responsePostDto,HttpStatus.OK);
    }

    @GetMapping("/memberDetail/{member_id}")
    public ResponseEntity<?> getAllMemberPost(@PathVariable("member_id") Long member_id){
        List<ResponsePostDto> responsePostDtos = postService.findAllByMember(Member.builder().id(member_id).build());
        return new ResponseEntity<List<ResponsePostDto>>(responsePostDtos,HttpStatus.OK);
    }




}
