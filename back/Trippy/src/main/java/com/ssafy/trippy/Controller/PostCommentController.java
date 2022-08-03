package com.ssafy.trippy.Controller;

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

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@Slf4j
public class PostCommentController {

    private final PostCommentServiceImpl postCommentService;
    private final String SUCCESS = "OK";
    private final String FAIL = "ERROR";


    @PostMapping(value="/comments")
    public ResponseEntity<?> createComment(@RequestBody ResponsePostCommentDto responsePostCommentDto){
        postCommentService.createComment(responsePostCommentDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }


}
