package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestLikePostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Service.LikePostService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/auth/likepost")
public class LikePostController {

    private final LikePostService likePostService;

    private final MemberService memberService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "ERROR";

    @PostMapping
    public ResponseEntity<?> saveLikePost(HttpServletRequest request, @RequestBody RequestLikePostDto requestLikePostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            if (likePostService.existsByMemberIdAndPostId(memberId, requestLikePostDto.getPostId())) {
                return new ResponseEntity<>("이미 좋아요 누른 게시글입니다", HttpStatus.NOT_FOUND);
            } else {
                requestLikePostDto.setMemberId(memberId);
                likePostService.saveLikePost(requestLikePostDto);
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("좋아요를 누를 수 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getLikePosts(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponsePostDto> responsePostDtos = likePostService.getLikePosts(memberId);
            if (responsePostDtos.size() == 0) {
                return new ResponseEntity<>("좋아요 누른 게시글이 없습니다", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
            }
        }catch(Exception e){
            return new ResponseEntity<>(FAIL,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping
    public ResponseEntity<?> removeLikePost(HttpServletRequest request, @RequestBody RequestLikePostDto requestLikePostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestLikePostDto.setMemberId(memberId);
        likePostService.deleteLikePost(requestLikePostDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/chk/{post_id}")
    public ResponseEntity<?> chkLikepost(HttpServletRequest request,@PathVariable("post_id") Long postId){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        return new ResponseEntity<>(likePostService.existsByMemberIdAndPostId(memberId,postId),HttpStatus.OK);
    }

    @GetMapping("/posts/Like/{postId}")
    public ResponseEntity<?> getLikePostCnt(@PathVariable("postId") Long postId){
        try {
            int cnt = likePostService.getLikeCount(postId);
            return new ResponseEntity<>(cnt, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }
}
