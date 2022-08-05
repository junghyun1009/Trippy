package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponseLikePostDto;
import com.ssafy.trippy.Service.LikePostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/likepost")
public class LikePostController {

    private final LikePostService likePostService;
    private static final String SUCCESS = "success";

    @PostMapping("/api")
    public ResponseEntity<?> saveLikePost(@RequestBody RequestLikePostDto requestLikePostDto){
        likePostService.saveLikePost(requestLikePostDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/api/{member_id}")
    public ResponseEntity<?> getLikePosts(@PathVariable("member_id") Long memberId){
        List<ResponseLikePostDto> responseLikePostDtos = likePostService.getLikePosts(memberId);
        return new ResponseEntity<>(responseLikePostDtos, HttpStatus.OK);
    }

    @DeleteMapping("/api")
    public ResponseEntity<?> removeLikePost(@RequestBody RequestLikePostDto requestLikePostDto){
        likePostService.deleteLikePost(requestLikePostDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
