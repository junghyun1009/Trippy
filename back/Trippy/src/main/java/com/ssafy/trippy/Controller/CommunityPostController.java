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
    private static final String SUCCESS = "success";

    @PostMapping
    public ResponseEntity<?> saveCommunityPost(@RequestBody @Valid RequestCommunityPostDto requestCommunityPostDto){
        communityPostService.saveCommunityPost(requestCommunityPostDto);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/{community_post_id}")
    public ResponseEntity<?> deleteCommunityPost(@PathVariable("community_post_id") Long id){
        communityPostService.deleteCommunityPost(id);
        return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/{community_post_id}")
    public ResponseEntity<?> updateCommunityPost(@PathVariable("community_post_id") Long community_post_id,@RequestBody @Valid UpdateCommunityPostDto updateCommunityPostDto){
        communityPostService.updateCommunityPost(community_post_id, updateCommunityPostDto);
        return  new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllCommunityPostList(){
        List<ResponseCommunityPostDto> responsePostDtos = communityPostService.getAllCommunityPost();
        return  new ResponseEntity<List<ResponseCommunityPostDto>>(responsePostDtos, HttpStatus.OK);
    }

    @GetMapping("/{community_post_id}")
    public ResponseEntity<?> detailCommunityPost(@PathVariable("community_post_id") Long id){
        ResponseCommunityPostDto responseCommunityPostDto = communityPostService.findCommunityPost(id);
        return new ResponseEntity<ResponseCommunityPostDto>(responseCommunityPostDto, HttpStatus.OK);
    }
}
