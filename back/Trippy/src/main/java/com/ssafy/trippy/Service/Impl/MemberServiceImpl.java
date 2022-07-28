package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    public Member signup(Member member) {
        String rawPassword = member.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        member.encodePassword(encodedPassword);
        return memberRepository.save(member);
    }
}
