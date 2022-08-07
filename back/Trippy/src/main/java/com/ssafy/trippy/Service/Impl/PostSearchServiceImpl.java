package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Dto.Request.SearchRequestDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostSearchServiceImpl {
    private final PostRepository postRepository;

    public List<ResponsePostDto> search(SearchRequestDto searchRequestDto){
        return postRepository.findAllByTitleContainingAndCompany(searchRequestDto.getTitle(), searchRequestDto.getCompany());
    }
}
