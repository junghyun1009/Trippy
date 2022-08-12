package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Service.Impl.PostSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {
    private final PostSearchServiceImpl postSearchService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam("title") String title,
                                    @RequestParam("company") int company,
                                    @RequestParam("locationId") Long locationId){
        SearchRequestDto searchRequestDto = SearchRequestDto.builder()
                .company(company)
                .title(title)
                .locationId(locationId)
                .build();
        List<ResponsePostDto> post = new ArrayList<>();
        try{
            post =  postSearchService.searchPost(searchRequestDto);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(FAIL,HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<List<ResponsePostDto>>(post, HttpStatus.OK);
    }
}
