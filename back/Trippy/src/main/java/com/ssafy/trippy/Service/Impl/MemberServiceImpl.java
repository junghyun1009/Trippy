package com.ssafy.trippy.Service.Impl;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
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

    private final JwtProvider jwtProvider;

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
    public ResponseLoginDto login(String email, String password) {
        Member member = memberRepository.findByEmail(email).get();
        if(member == null){
            throw new IllegalArgumentException("email을 확인하세요.");
        }
        if(!passwordEncoder.matches(password, member.getPassword())){
            throw new IllegalArgumentException("password를 확인하세요.");
        }
        String accessToken = jwtProvider.createAccessToken(member.getUsername(), member.getRoles());
        String refreshToken = jwtProvider.createRefreshToken(member.getUsername(), member.getRoles());
        return new ResponseLoginDto(accessToken, refreshToken);
    }

    @Override
    public ResponseLoginDto reIssueAccessToken(String email, String refreshToken) {
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));
        jwtProvider.checkRefreshToken(email, refreshToken);
        String accessToken = jwtProvider.createAccessToken(member.getEmail(), member.getRoles());
        return new ResponseLoginDto(accessToken, refreshToken);
    }

    @Override
    public boolean chkDuplicate(String email){
        return memberRepository.existsByEmail(email);
    }

    @Override
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }

    @Override
    public void updateMember(Long id, UpdateMemberDto updateMemberDto){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        member.update(updateMemberDto);
        memberRepository.save(member);
    }

    @Override
    public ResponseMemberDto selectMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("아이디가 존재하지 않습니다."));
        return new ResponseMemberDto(member);
    }

    @Override
    public void logout(Long id, String accessToken) {
        Member member = memberRepository.findById(id).get();
        jwtProvider.logout(member.getEmail(), accessToken);
    }
}
