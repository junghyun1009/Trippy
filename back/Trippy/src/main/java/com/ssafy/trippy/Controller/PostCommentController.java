package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.PostComment;
import com.ssafy.trippy.Dto.Request.RequestPostCommentDto;
import com.ssafy.trippy.Dto.Response.ResponsePostCommentDto;
import com.ssafy.trippy.Service.Impl.PostCommentServiceImpl;
import com.ssafy.trippy.Service.PostCommentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
@RequestMapping("comments")
public class PostCommentController {

    private final PostCommentServiceImpl postCommentService;
    private final String SUCCESS = "OK";
    private final String FAIL = "ERROR";


    // 댓글 등록
    @PostMapping
    public ResponseEntity<?> createComment(@RequestBody ResponsePostCommentDto responsePostCommentDto) {
        try {
            postCommentService.createComment(responsePostCommentDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    // 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable("commentId") Long commentId) {
        try {
            postCommentService.deleteComment(commentId);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

    }

    // 게시판에 달린 모든 댓글 조회
    @GetMapping("/{postId}")
    public ResponseEntity<?> getAllPostComment(@PathVariable("postId") Long postId) {
        List<RequestPostCommentDto> postCommentList = new ArrayList<>();
        try {
            postCommentList = postCommentService.findPostCommentByPostId(postId);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<List<RequestPostCommentDto>>(postCommentList, HttpStatus.OK);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> updateComment(@PathVariable("commentId") Long commentId, @RequestBody @Valid RequestPostCommentDto requestPostCommentDto) {
        try {
            postCommentService.updateComment(commentId, requestPostCommentDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

}
