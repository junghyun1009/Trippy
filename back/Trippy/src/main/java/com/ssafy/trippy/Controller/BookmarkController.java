package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestBookmarkDto;
import com.ssafy.trippy.Dto.Response.ResponseBookmarkDto;
import com.ssafy.trippy.Service.BookmarkService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@CrossOrigin("*")
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;
    private static final String SUCCESS = "success";

    @PostMapping("/api")
    public ResponseEntity<?> saveBookmark(@RequestBody RequestBookmarkDto requestBookmarkDto){
        bookmarkService.saveBookmark(requestBookmarkDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/api/{member_id}")
    public ResponseEntity<?> getBookmarks(@PathVariable("member_id") Long memberId){
        List<ResponseBookmarkDto> responseBookmarkDtos = bookmarkService.getBookmarks(memberId);
        return new ResponseEntity<>(responseBookmarkDtos, HttpStatus.OK);
    }

    @DeleteMapping("/api")
    public ResponseEntity<?> removeBookmark(@RequestBody RequestBookmarkDto requestBookmarkDto){
        bookmarkService.deleteBookmark(requestBookmarkDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
