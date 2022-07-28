package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Repository.MemberRepository;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseMemberDto signup(RequestMemberDto requestMemberDto) {
        if(chkDuplicate(requestMemberDto.getEmail())){
            throw new IllegalArgumentException("이메일이 중복됩니다.");
        }
        String rawPassword = requestMemberDto.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        requestMemberDto.encodePassword(encodedPassword);
        return new ResponseMemberDto(memberRepository.save(requestMemberDto.toEntity()));
    }

    @Override
    public ResponseMemberDto login(String email,String password) {
        ResponseMemberDto responseMemberDto = new ResponseMemberDto(memberRepository.findByEmail(email).get());
        if(responseMemberDto == null){
            throw new IllegalArgumentException("email을 확인하세요.");
        }
        if(!passwordEncoder.matches(password, responseMemberDto.getPassword())){
            throw new IllegalArgumentException("password를 확인하세요.");
        }
        return responseMemberDto;
    }

    public boolean chkDuplicate(String email){
        return memberRepository.existsByEmail(email);
    }
}
