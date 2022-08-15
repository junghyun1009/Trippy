package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Service.BookmarkService;
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
@RequestMapping("/auth/bookmark")
public class BookmarkController {

    private final MemberService memberService;
    private final BookmarkService bookmarkService;
    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "ERROR";

    @PostMapping("/{community_post_id}")
    public ResponseEntity<?> saveBookmark(HttpServletRequest request, @PathVariable("community_post_id") Long communityPostId) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            if (bookmarkService.findBookMarKByMemberAndCommunityPost(memberId, communityPostId)) {
                return new ResponseEntity<>("북마크를 이미 설정하였습니다", HttpStatus.OK);
            } else {
                RequestBookmarkDto requestBookmarkDto = new RequestBookmarkDto(communityPostId, memberId);
                bookmarkService.saveBookmark(requestBookmarkDto);
                return new ResponseEntity<>("북마크를 설정하였습니다", HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("북마크를 설정할 수 없습니다", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public ResponseEntity<?> getBookmarks(HttpServletRequest request) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try{
        List<ResponseCommunityPostDto> responseCommunityPostDtos = bookmarkService.getBookmarks(memberId);
            if (responseCommunityPostDtos.size() == 0) {
                return new ResponseEntity<>("북마크한 게시글이 없습니다.",HttpStatus.OK);
            }else{
        return new ResponseEntity<>(responseCommunityPostDtos, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(FAIL, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{community_post_id}")
    public ResponseEntity<?> removeBookmark(HttpServletRequest request, @PathVariable("community_post_id") Long communityPostId) {
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        try {
            RequestBookmarkDto requestBookmarkDto = new RequestBookmarkDto(communityPostId, memberId);
            bookmarkService.deleteBookmark(requestBookmarkDto);
            return new ResponseEntity<>("북마크를 삭제하였습니다.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("북마크를 삭제할 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
