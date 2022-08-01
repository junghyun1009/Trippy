package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Service.MemberService;
import com.ssafy.trippy.Service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<?> savePost(@RequestBody @Valid RequestPostDto requestPostDto){
        postService.savePost(requestPostDto);
        List<ResponsePostDto> list = postService.findAll();
        return new ResponseEntity<List<ResponsePostDto>>(list,HttpStatus.OK);
    }


}
