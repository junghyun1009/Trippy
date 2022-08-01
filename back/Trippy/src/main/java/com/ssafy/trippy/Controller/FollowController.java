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
@Slf4j
@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowService followService;

    @PostMapping
    public ResponseEntity<?> follow(@RequestBody RequestFollowDto requestFollowDto){
        ResponseFollowDto responseFollowDto = followService.follow(requestFollowDto);
        return new ResponseEntity<>(responseFollowDto, HttpStatus.OK);
    }

    @PostMapping("/undo")
    public ResponseEntity<?> unfollow(@RequestBody RequestFollowDto requestFollowDto){
        followService.unfollow(requestFollowDto);
        return new ResponseEntity<String>("언팔성공", HttpStatus.OK);
    }

    @GetMapping("/follower")
    public ResponseEntity<?> getFollowers(@PathVariable Long memberId){
        List<ResponseFollowDto> responseFollowDtos = followService.getFollowerList(memberId);
        return new ResponseEntity<>(responseFollowDtos, HttpStatus.OK);
    }
}
