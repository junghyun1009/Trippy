package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Response.ResponseBadgeDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Response.ResponseSavepostDto;
import com.ssafy.trippy.Dto.Update.UpdatePostDto;
import com.ssafy.trippy.Service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    private final MemberService memberService;

    private final BadgeService badgeService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";


    @PostMapping("/auth/posts")
    public ResponseEntity<?> savePost(HttpServletRequest request, @RequestPart("post") @Valid RequestPostDto requestPostDto
            , @RequestPart("images") List<MultipartFile> images) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestPostDto.setMember_id(memberId);
        try {
            Long id = postService.savePost(requestPostDto, images);
            ResponseSavepostDto responseSavepostDto = new ResponseSavepostDto(id);
            Long cnt = postService.cntPostsByMemberId(memberId);
            if(cnt==1L){
                ResponseBadgeDto responseBadgeDto = badgeService.saveBadge(2L,memberId);
                responseSavepostDto.addBadge(responseBadgeDto);
            }
            return new ResponseEntity<>(responseSavepostDto, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("저장할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/auth/posts/{post_id}")
    public ResponseEntity<?> deletePost(HttpServletRequest request, @PathVariable("post_id") Long post_id) {
        ResponsePostDto post = postService.findPostId(post_id);
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            if (memberId == post.getMemberId()) {
                postService.deletePost(post_id);
                return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("본인이 작성한 글만 삭제가 가능합니다.", HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("삭제할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/auth/posts/{post_id}")
    public ResponseEntity<?> updatePost(HttpServletRequest request, @PathVariable("post_id") Long
            post_id, @RequestPart("post") @Valid RequestPostDto requestPostDto
            , @RequestPart("images") List<MultipartFile> images) {
        ResponsePostDto post = postService.findPostId(post_id);
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            if (memberId == post.getMemberId()) {
                Long id = postService.updatePost(post_id, requestPostDto, images);
                return new ResponseEntity<>(id, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("본인이 작성한 글만 수정이 가능합니다.", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("수정할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/posts")
    public ResponseEntity<?> getAllPost() {
        List<ResponsePostDto> responsePostDtos;
        try {
            responsePostDtos = postService.findAll();
            if (responsePostDtos.size() == 0) {
                return new ResponseEntity<>("작성한 글이 없습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("게시글이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/detail/{post_id}")
    public ResponseEntity<?> detailPost(@PathVariable("post_id") Long post_id) {
        ResponsePostDto responsePostDto;
        try {
            responsePostDto = postService.findPostId(post_id);
            return new ResponseEntity<>(responsePostDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/posts/memberDetail")
    public ResponseEntity<?> getAllMemberPost(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponsePostDto> responsePostDtos = postService.findAllByMember(Member.builder().id(memberId).build());
            if (responsePostDtos.size() == 0) {
                return new ResponseEntity<>("작성한 게시글이 없습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시물이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts/{countryName}/{cityName}")
    public ResponseEntity<?> getAllPostByCountryNameAndCityName(@PathVariable("countryName") String countryName,
                                                                @PathVariable("cityName") String cityName) {
        try {
            Location location = Location.builder().countryName(countryName).cityName(cityName).build();
            List<ResponsePostDto> responsePostDtos = postService.findByCity(location);
            return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("해당 게시물을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
