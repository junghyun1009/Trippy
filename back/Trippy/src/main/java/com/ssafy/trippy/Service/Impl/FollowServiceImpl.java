package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Dto.Exception.CustomApiException;
import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Repository.FollowRepository;
import com.ssafy.trippy.Service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class FollowServiceImpl implements FollowService {
    private final FollowRepository followRepository;

    @Override
    public ResponseFollowDto follow(RequestFollowDto requestFollowDto) {
        Follow follow = requestFollowDto.toEntity();
        return new ResponseFollowDto(followRepository.save(follow));
    }
    @Override
    public void unfollow(RequestFollowDto requestFollowDto) {
        followRepository.deleteByFollowerIdAndFollowingId(requestFollowDto.getFollowerId(),requestFollowDto.getFollowingId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseFollowDto> getFollowers(Long memberId) {
        List<Follow> followList = followRepository.findAllByFollowerId(memberId);
        List<ResponseFollowDto> responseFollowDtoList = new ArrayList<>();
        for (Follow follow : followList) {
            responseFollowDtoList.add(new ResponseFollowDto(follow));
        }
        return responseFollowDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseFollowDto> getFollowings(Long memberId) {
        List<Follow> followList = followRepository.findAllByFollowingId(memberId);
        List<ResponseFollowDto> responseFollowDtoList = new ArrayList<>();
        for (Follow follow : followList) {
            responseFollowDtoList.add(new ResponseFollowDto(follow));
        }
        return responseFollowDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public Long getFollowersCnt(Long memberId) {
        return followRepository.countByFollowerId(memberId);
    }

    @Transactional(readOnly = true)
    @Override
    public Long getFollowingsCnt(Long memberId) {
        return followRepository.countByFollowingId(memberId);
    }

}
