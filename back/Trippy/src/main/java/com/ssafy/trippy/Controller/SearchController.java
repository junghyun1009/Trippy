package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.CommunityPostSearchRequestDto;
import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Service.Impl.PostSearchServiceImpl;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SearchController {
    private final PostSearchServiceImpl postSearchService;
    private final MemberService memberService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @GetMapping("/search")
    public ResponseEntity<?> search(@RequestParam(value = "title", required = false) String title,
                                    @RequestParam(value = "company", required = false) Integer company,
                                    @RequestParam(value = "transportId", required = false) Long transportId,
                                    @RequestParam(value = "countryName", required = false) String countryName,
                                    @RequestParam(value = "cityName", required = false) String cityName) {
        if (company == null) company = 0;
        if (transportId == null) transportId = 0L;

        SearchRequestDto searchRequestDto = SearchRequestDto.builder()
                .company(company)
                .countryName(countryName)
                .cityName(cityName)
                .title(title)
                .transportId(transportId)
                .build();
        List<ResponsePostDto> post;
        try {
            post = postSearchService.searchPost(searchRequestDto);
            for (ResponsePostDto responsePostDto : post) {
                ResponseMemberDto member = memberService.selectMember(responsePostDto.getMemberId());
                responsePostDto.setMemberImg(member.getImg_link());
                responsePostDto.setMemberId(member.getId());
            }
            return new ResponseEntity<List<ResponsePostDto>>(post, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("해당 게시물을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/search/communityPost")
    public ResponseEntity<?> Communitysearch(@RequestParam(value = "countryName", required = false) String countryName,
                                             @RequestParam(value = "cityName", required = false) String cityName) {

        CommunityPostSearchRequestDto searchRequestDto = CommunityPostSearchRequestDto.builder()
                .countryName(countryName)
                .cityName(cityName)
                .build();
        List<ResponseCommunityPostDto> communityPostDtos;
        try {
            communityPostDtos = postSearchService.searchCommunityPost(searchRequestDto);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("해당 게시물을 찾을 수 없습니다.", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(communityPostDtos, HttpStatus.OK);
    }
}
