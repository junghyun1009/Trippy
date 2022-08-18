package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseBadgeDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Response.ResponseSavepostDto;
import com.ssafy.trippy.Service.BadgeService;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Service.CommunityPostService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommunityPostController {

    private final CommunityPostService communityPostService;

    private final MemberService memberService;

    private final BadgeService badgeService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @PostMapping("/auth/community")
    public ResponseEntity<?> saveCommunityPost(HttpServletRequest request, @RequestBody @Valid RequestCommunityPostDto requestCommunityPostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        requestCommunityPostDto.setMember_id(memberId);
        try {
            Long id = communityPostService.saveCommunityPost(requestCommunityPostDto);
            ResponseSavepostDto responseSavepostDto = new ResponseSavepostDto(id);
            Long cnt = communityPostService.cntCommunityPostsByMemberId(memberId);
            if(cnt==1L){
                ResponseBadgeDto responseBadgeDto = badgeService.saveBadge(3L,memberId);
                responseSavepostDto.addBadge(responseBadgeDto);
            }
            return new ResponseEntity<>(responseSavepostDto, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/auth/community/{community_post_id}")
    public ResponseEntity<?> deleteCommunityPost(HttpServletRequest request, @PathVariable("community_post_id") Long community_post_id) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        ResponseCommunityPostDto communityPost = communityPostService.findCommunityPost(community_post_id);
        try {
            if (memberId != null && memberId.equals(communityPost.getMemberId())) {
                communityPostService.deleteCommunityPost(community_post_id);
            } else {
                return new ResponseEntity<>("본인이 작성한 글만 삭제가 가능합니다", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @PutMapping("/auth/community/{community_post_id}")
    public ResponseEntity<?> updateCommunityPost(HttpServletRequest request, @PathVariable("community_post_id") Long community_post_id, @RequestBody @Valid UpdateCommunityPostDto updateCommunityPostDto) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        ResponseCommunityPostDto communityPost = communityPostService.findCommunityPost(community_post_id);
        updateCommunityPostDto.setMemberId(memberId);
        try {
            if (memberId != null && memberId.equals(communityPost.getMemberId())) {
                communityPostService.updateCommunityPost(community_post_id, updateCommunityPostDto);
            } else {
                return new ResponseEntity<>("본인이 작성한 글만 수정이 가능합니다", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/community")
    public ResponseEntity<?> getAllCommunityPostList() {
        try {
            List<ResponseCommunityPostDto> responsePostDtos = communityPostService.getAllCommunityPost();
            if (responsePostDtos.size() == 0) {
                return new ResponseEntity<>("게시글이 없습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responsePostDtos, HttpStatus.OK);

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시글이 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/community/{community_post_id}")
    public ResponseEntity<?> detailCommunityPost(@PathVariable("community_post_id") Long id) {
        try {
            ResponseCommunityPostDto responseCommunityPostDto = communityPostService.findCommunityPost(id);
            return new ResponseEntity<>(responseCommunityPostDto, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/community/memberDetail")
    public ResponseEntity<?> getAllMemberCommunityPost(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            List<ResponseCommunityPostDto> responsePostCommunityDtos = communityPostService.getCommunityPostByMemberId(memberId);
            if (responsePostCommunityDtos.size() == 0) {
                return new ResponseEntity<>("작성한 게시글이 없습니다.", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(responsePostCommunityDtos, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("게시물이 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
