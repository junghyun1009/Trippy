package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestPostSearchDto;
import com.ssafy.trippy.Dto.Response.ResponsePostSearchDto;
import com.ssafy.trippy.Service.Impl.PostSearchServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchController {

    private final PostSearchServiceImpl postSearchService;
    private static final String SUCCESS = "OK";
    private static final String FAIL = "ERROR";

    @GetMapping("/{title}/{company}")
    public ResponseEntity<?> search(@PathVariable("title") String title,
                                    @PathVariable("company") int company) {
        RequestPostSearchDto requestPostSearchDto = RequestPostSearchDto.builder()
                .company(company)
                .title(title).build();
        List<ResponsePostSearchDto> responsePostSearchDtos = new ArrayList<>();
        try {
            responsePostSearchDtos = postSearchService.searchList(requestPostSearchDto);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<String>(FAIL, HttpStatus.METHOD_NOT_ALLOWED);
        }
        return new ResponseEntity<List<ResponsePostSearchDto>>(responsePostSearchDtos, HttpStatus.OK);

    }
}
