package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Service.FollowService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/auth/follow")
@CrossOrigin("*")
@Slf4j
@RequiredArgsConstructor
@RestController
public class FollowController {
    private final FollowService followService;

    private final MemberService memberService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @PostMapping
    public ResponseEntity<?> follow(HttpServletRequest request, @RequestBody RequestFollowDto requestFollowDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            if (followService.existsByFollowerIdAndFollowingId(memberId, requestFollowDto.getFollowingId())) {
                return new ResponseEntity<>("이미 팔로우 했습니다", HttpStatus.OK);
            } else {
                requestFollowDto.setFollowerId(memberId);
                ResponseFollowDto responseFollowDto = followService.follow(requestFollowDto);
                return new ResponseEntity<>(responseFollowDto, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/undo")
    public ResponseEntity<?> unfollow(HttpServletRequest request, @RequestBody RequestFollowDto requestFollowDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestFollowDto.setFollowerId(memberId);
        followService.unfollow(requestFollowDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/following")
    public ResponseEntity<?> getFollowings(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponseMemberDto> responseMemberDtos = followService.getFollowers(memberId);
            if (responseMemberDtos.size() == 0) {
                return new ResponseEntity<>("팔로우한 유저가 없습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/follower")
    public ResponseEntity<?> getFollowers(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponseMemberDto> responseMemberDtos = followService.getFollowings(memberId);
            if (responseMemberDtos.size() == 0) {
                return new ResponseEntity<>("본인을 팔로우한 유저가 없습니다", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(FAIL,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/following/{memberId}")
    public ResponseEntity<?> getFollowingsByMemberId(@PathVariable Long memberId) {
        try {
            List<ResponseMemberDto> responseMemberDtos = followService.getFollowers(memberId);
            if (responseMemberDtos.size() == 0) {
                return new ResponseEntity<>("팔로워한 유저가 없습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/follower/{memberId}")
    public ResponseEntity<?> getFollowersByMemberId(@PathVariable Long memberId) {
        try {
            List<ResponseMemberDto> responseMemberDtos = followService.getFollowings(memberId);
            if (responseMemberDtos.size() == 0) {
                return new ResponseEntity<>("본인을 팔로우한 유저가 없습니다", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responseMemberDtos, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(FAIL,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/follower/cnt")
    public ResponseEntity<?> getFollowingsCnt(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        Long responseCnt = followService.getFollowersCnt(memberId);
        return new ResponseEntity<>(responseCnt, HttpStatus.OK);
    }

    @GetMapping("/following/cnt")
    public ResponseEntity<?> getFollowersCnt(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        Long responseCnt = followService.getFollowingsCnt(memberId);
        return new ResponseEntity<>(responseCnt, HttpStatus.OK);
    }

    @GetMapping("/follower/cnt/{memberId}")
    public ResponseEntity<?> getFollowingsCntByMemeberId(@PathVariable Long memberId){
        Long responseCnt = followService.getFollowersCnt(memberId);
        return new ResponseEntity<>(responseCnt, HttpStatus.OK);
    }

    @GetMapping("/following/cnt/{memberId}")
    public ResponseEntity<?> getFollowersCntByMemeberId(@PathVariable Long memberId){
        Long responseCnt = followService.getFollowingsCnt(memberId);
        return new ResponseEntity<>(responseCnt, HttpStatus.OK);
    }

    @GetMapping("/chk/{following_id}")
    public ResponseEntity<?> chkFollowing(HttpServletRequest request,@PathVariable("following_id") Long followingId){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        return new ResponseEntity<>(followService.existsByFollowerIdAndFollowingId(memberId,followingId),HttpStatus.OK);
    }
}
