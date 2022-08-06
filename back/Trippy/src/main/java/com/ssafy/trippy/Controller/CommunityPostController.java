package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Service.CommunityPostService;
import com.ssafy.trippy.Service.Impl.CommunityPostServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/community")
public class CommunityPostController {

    private final CommunityPostService communityPostService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @PostMapping
    public ResponseEntity<?> saveCommunityPost(@RequestBody @Valid RequestCommunityPostDto requestCommunityPostDto) {
        try {
            communityPostService.saveCommunityPost(requestCommunityPostDto);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{community_post_id}")
    public ResponseEntity<?> deleteCommunityPost(@PathVariable("community_post_id") Long community_post_id) {
        try {
            communityPostService.deleteCommunityPost(community_post_id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/{community_post_id}")
    public ResponseEntity<?> updateCommunityPost(@PathVariable("community_post_id") Long community_post_id, @RequestBody @Valid RequestCommunityPostDto requestCommunityPostDto) {
        try {
            communityPostService.updateCommunityPost(community_post_id, requestCommunityPostDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCommunityPostList() {
        List<ResponseCommunityPostDto> responsePostDtos = communityPostService.getAllCommunityPost();
        return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
    }

    @GetMapping("/{community_post_id}")
    public ResponseEntity<?> detailCommunityPost(@PathVariable("community_post_id") Long id) {
        ResponseCommunityPostDto responseCommunityPostDto = communityPostService.findCommunityPost(id);
        return new ResponseEntity<>(responseCommunityPostDto, HttpStatus.OK);
    }
}
