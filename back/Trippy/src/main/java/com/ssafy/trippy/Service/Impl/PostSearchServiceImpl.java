package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestPostDto;
import com.ssafy.trippy.Dto.Request.RequestPostSearchDto;
import com.ssafy.trippy.Dto.Response.ResponsePostDto;
import com.ssafy.trippy.Dto.Response.ResponsePostSearchDto;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostSearchServiceImpl {
    private final PostRepository postRepository;

    private final MemberRepository memberRepository;
    public List<ResponsePostSearchDto> searchList(RequestPostSearchDto requestPostSearchDto) {
        List<RequestPostDto> searchDtoList = postRepository.search(requestPostSearchDto);
        Optional<Member> member = memberRepository.findByEmail(requestPostSearchDto.getEmail());
        List<ResponsePostSearchDto> responsePostSearchDtos = new ArrayList<>();
        for (RequestPostDto requestPostDto : searchDtoList) {
            ResponsePostSearchDto dto = ResponsePostSearchDto.builder()
                    .startDate(requestPostDto.getStartDate())
                    .email(member.get().getEmail())
                    .title(requestPostDto.getTitle()).build();
            responsePostSearchDtos.add(dto);
        }
        return responsePostSearchDtos;
    }

}
