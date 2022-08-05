package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Location;
import com.ssafy.trippy.Dto.Request.RequestCommunityPostDto;
import com.ssafy.trippy.Dto.Response.ResponseCommunityPostDto;
import com.ssafy.trippy.Dto.Update.UpdateCommunityPostDto;
import com.ssafy.trippy.Domain.CommunityPost;
import com.ssafy.trippy.Repository.CommunityPostRepository;
import com.ssafy.trippy.Repository.LocationRepository;
import com.ssafy.trippy.Service.CommunityPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Transactional(readOnly = true)
    public List<ResponseCommunityPostDto> getAllCommunityPost() {
        List<CommunityPost> communityPosts = communityPostRepository.findAll();
        List<ResponseCommunityPostDto> communityPostDtos = new ArrayList<>();
        for (CommunityPost communityPost : communityPosts) {
            Location location = locationRepository.findById(communityPost.getLocation().getId()).get();
            ResponseCommunityPostDto responseCommunityPostDto = ResponseCommunityPostDto.builder()
                    .category(communityPost.getCategory())
                    .cityName(location.getCityName())
                    .countryName(location.getCountryName())
                    .description(communityPost.getDescription())
                    .endAge(communityPost.getEndAge())
                    .startAge(communityPost.getStartAge())
                    .endDate(communityPost.getEndDate())
                    .gender(communityPost.getGender())
                    .isLocal(communityPost.isLocal())
                    .meetingTime(communityPost.getMeetingTime())
                    .recruitCurrentVolume(communityPost.getRecruitCurrentVolume())
                    .recruitVolume(communityPost.getRecruitVolume())
                    .startDate(communityPost.getStartDate())
                    .title(communityPost.getTitle())
                    .build();
            communityPostDtos.add(responseCommunityPostDto);
        }
        return communityPostDtos;
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseCommunityPostDto findCommunityPost(Long id) {
        Optional<CommunityPost> communityPost = communityPostRepository.findById(id);
        return new ResponseCommunityPostDto(communityPost.get());
    }
}
