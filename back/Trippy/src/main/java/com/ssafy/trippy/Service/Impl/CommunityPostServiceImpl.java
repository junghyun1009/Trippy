package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Request.RequestLocationDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Repository.CommunityPostRepository;
import com.ssafy.trippy.Repository.LocationRepository;
import com.ssafy.trippy.Service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommunityPostServiceImpl implements CommunityPostService {

    private final CommunityPostRepository communityPostRepository;
    private final LocationRepository locationRepository;


    @Transactional
    @Override
    public Long saveCommunityPost(RequestCommunityPostDto requestCommunityPostDto) {
        return communityPostRepository.save(requestCommunityPostDto.toEntity()).getId();
    }

    @Transactional
    @Override
    public void deleteCommunityPost(Long id) {
        communityPostRepository.deleteById(id);

    }

    @Transactional
    @Override
    public void updateCommunityPost(Long id, UpdateCommunityPostDto updateCommunityPostDto) {
        CommunityPost communityPost = communityPostRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));
        System.out.println("updateCommunityPostDto.getLocation_id() = " + updateCommunityPostDto.toString());
        Optional<Location> location = locationRepository.findById(updateCommunityPostDto.getLocation_id());

        communityPost.update(updateCommunityPostDto.getTitle(),
                updateCommunityPostDto.getDescription(),
                updateCommunityPostDto.getCategory(),
                updateCommunityPostDto.getMeetingTime(),
                updateCommunityPostDto.getStartDate(),
                updateCommunityPostDto.getEndDate(),
                updateCommunityPostDto.getRecruitVolume(),
                updateCommunityPostDto.getRecruitCurrentVolume(),
                updateCommunityPostDto.getStartAge(),
                updateCommunityPostDto.getEndAge(),
                updateCommunityPostDto.getGender(),
                updateCommunityPostDto.isLocal(),
                location.get());
    }

    @Override
    public List<ResponseCommunityPostDto> getAllCommunityPost() {
        List<CommunityPost> communityPosts = communityPostRepository.findAll();
        List<ResponseCommunityPostDto> communityPostDtos = communityPosts.stream()
                .map(m -> new ResponseCommunityPostDto())
                .collect(Collectors.toList());
        return communityPostDtos;
    }

    @Override
    public ResponseCommunityPostDto findCommunityPost(Long id) {
        Optional<CommunityPost> communityPost = communityPostRepository.findById(id);
        return new ResponseCommunityPostDto(communityPost.get());
    }
}
