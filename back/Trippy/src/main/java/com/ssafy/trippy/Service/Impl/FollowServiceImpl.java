package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Exception.CustomApiException;
import com.ssafy.trippy.Dto.Request.RequestFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Repository.FollowRepository;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Service.FollowService;
import com.ssafy.trippy.Service.S3Uploader;
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

    private final MemberRepository memberRepository;

    private final S3Uploader s3Uploader;

    @Override
    public ResponseFollowDto follow(RequestFollowDto requestFollowDto) {
        Follow follow = requestFollowDto.toEntity();
        return new ResponseFollowDto(followRepository.save(follow));
    }
    @Override
    public void unfollow(RequestFollowDto requestFollowDto) {
        if(!followRepository.existsByFollowerIdAndFollowingId(requestFollowDto.getFollowerId(),requestFollowDto.getFollowingId())){
            throw new IllegalArgumentException("존재하지 않는 팔로우");
        }
        followRepository.deleteByFollowerIdAndFollowingId(requestFollowDto.getFollowerId(),requestFollowDto.getFollowingId());
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseMemberDto> getFollowers(Long memberId) {
        List<Follow> followList = followRepository.findAllByFollowerId(memberId);
        List<ResponseMemberDto> responseMemberDtos = new ArrayList<>();
        for (Follow follow : followList) {
            Member member = memberRepository.findById(follow.getFollowing().getId()).orElseThrow();
            ResponseMemberDto responseMemberDto = new ResponseMemberDto(member);
            if(member.getImg_path()!=null)
                responseMemberDto.setImg_link(s3Uploader.getS3(member.getImg_path()));
            responseMemberDtos.add(responseMemberDto);

        }
        return responseMemberDtos;
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseMemberDto> getFollowings(Long memberId) {
        List<Follow> followList = followRepository.findAllByFollowingId(memberId);
        List<ResponseMemberDto> responseMemberDtos = new ArrayList<>();
        for (Follow follow : followList) {
            Member member = memberRepository.findById(follow.getFollower().getId()).orElseThrow();
            ResponseMemberDto responseMemberDto = new ResponseMemberDto(member);
            if(member.getImg_path()!=null)
                responseMemberDto.setImg_link(s3Uploader.getS3(member.getImg_path()));
            responseMemberDtos.add(responseMemberDto);
        }
        return responseMemberDtos;
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

    @Override
    public boolean existsByFollowerIdAndFollowingId(Long followerId, Long followingId) {
        if(followRepository.existsByFollowerIdAndFollowingId(followerId, followingId)){
            return true;
        }
        return false;
    }

}
