//package com.ssafy.trippy.Service.Impl;
//
//import com.ssafy.trippy.Dto.Exception.CustomApiException;
//import com.ssafy.trippy.Dto.Response.ResponseFollowDto;
//import com.ssafy.trippy.Entity.Follow;
//import com.ssafy.trippy.Entity.Member;
//import com.ssafy.trippy.Repository.FollowRepository;
//import com.ssafy.trippy.Service.FollowService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class FollowServiceImpl implements FollowService {
//    private final FollowRepository followRepository;
//
//    @Transactional
//    @Override
//    public void follow(long followingId, long followerId) {
//        if(followRepository.findFollowByFollowingAndFollower(followingId, followerId)!= null) throw new CustomApiException("이미 팔로우하셨습니다");
//        followRepository.follow(followerId, followerId);
//    }
//
//    @Transactional
//    @Override
//    public void unfollow(long followingId, long followerId) {
//        followRepository.unFollow(followingId, followerId);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<ResponseFollowDto> getFollowerList(Long memberId, Long followerId) {
//        List<Follow> followList = followRepository.findAllByFollowerIdAndMemberId(memberId, followerId);
//        List<ResponseFollowDto> responseFollowDtoList = new ArrayList<>();
//        for (Follow follow : followList) {
//            ResponseFollowDto dto = ResponseFollowDto.builder()
//                    .id(follow.getId())
//                    .follower(follow.getFollower())
//                    .following(follow.getFollowing()).build();
//            responseFollowDtoList.add(dto);
//        }
//        return responseFollowDtoList;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<ResponseFollowDto> getFollowingList(Long memberId, Long followingId) {
//        List<Follow> followList = followRepository.findAllByFollowingIdAndMemberId(memberId, followingId);
//        List<ResponseFollowDto> responseFollowDtoList = new ArrayList<>();
//        for (Follow follow : followList) {
//            ResponseFollowDto dto = ResponseFollowDto.builder()
//                    .id(follow.getId())
//                    .follower(follow.getFollower())
//                    .following(follow.getFollowing()).build();
//            responseFollowDtoList.add(dto);
//        }
//        return responseFollowDtoList;
//    }
//}
