package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Badge;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Domain.MemberBadge;
import com.ssafy.trippy.Dto.Response.ResponseBadgeDto;
import com.ssafy.trippy.Repository.BadgeRepository;
import com.ssafy.trippy.Repository.MemberBadgeRepository;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Service.BadgeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {
    private final MemberBadgeRepository memberBadgeRepository;

    private final MemberRepository memberRepository;

    private final BadgeRepository badgeRepository;
    @Override
    public ResponseBadgeDto saveBadge(Long badgeId, Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Badge badge = badgeRepository.findById(badgeId).orElseThrow();
        memberBadgeRepository.save(new MemberBadge(member,badge));
        return new ResponseBadgeDto(badge.getName());
    }

    @Override
    public List<ResponseBadgeDto> getBadgesByMemberId(Long memberId) {
        List<MemberBadge> memberBadges = memberBadgeRepository.findAllByMemberId(memberId);
        List<ResponseBadgeDto> responseBadgeDtos = new ArrayList<>();
        for (MemberBadge memberBadge:memberBadges){
            responseBadgeDtos.add(new ResponseBadgeDto(memberBadge.getBadge().getName()));
        }
        return responseBadgeDtos;
    }
}
