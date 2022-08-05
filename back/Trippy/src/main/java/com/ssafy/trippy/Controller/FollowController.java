package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Service.FollowService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/follow")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowService followService;
    private static final String SUCCESS = "success";

    @PostMapping
    public ResponseEntity<?> follow(@RequestBody RequestFollowDto requestFollowDto){
        ResponseFollowDto responseFollowDto = followService.follow(requestFollowDto);
        return new ResponseEntity<>(responseFollowDto, HttpStatus.OK);
    }

    @PostMapping("/undo")
    public ResponseEntity<?> unfollow(@RequestBody RequestFollowDto requestFollowDto){
        followService.unfollow(requestFollowDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/follower/{member_id}")
    public ResponseEntity<?> getFollowers(@PathVariable("member_id") Long memberId){
        List<ResponseFollowDto> responseFollowDtos = followService.getFollowers(memberId);
        return new ResponseEntity<>(responseFollowDtos, HttpStatus.OK);
    }

    @GetMapping("/following/{member_id}")
    public ResponseEntity<?> getFollowings(@PathVariable("member_id") Long memberId){
        List<ResponseFollowDto> responseFollowDtos = followService.getFollowings(memberId);
        return new ResponseEntity<>(responseFollowDtos, HttpStatus.OK);
    }

    @GetMapping("/follower/cnt/{member_id}")
    public ResponseEntity<?> getFollowersCnt(@PathVariable("member_id") Long memberId){
        Long responseCnt = followService.getFollowersCnt(memberId);
        return new ResponseEntity<Long>(responseCnt, HttpStatus.OK);
    }

    @GetMapping("/following/cnt/{member_id}")
    public ResponseEntity<?> getFollowingsCnt(@PathVariable("member_id") Long memberId){
        Long responseCnt = followService.getFollowingsCnt(memberId);
        return new ResponseEntity<Long>(responseCnt, HttpStatus.OK);
    }
}
