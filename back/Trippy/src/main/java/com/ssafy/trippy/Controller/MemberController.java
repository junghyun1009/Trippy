package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final JwtProvider jwtTokenProvider;
    private final MemberService memberService;


    @PostMapping("/join")
    public String join(@RequestBody RequestMemberDto requestMemberDto){
        ResponseMemberDto responseMemberDto = memberService.signup(requestMemberDto);
        return responseMemberDto.toString();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {

        if(!user.containsKey("email") || !user.containsKey("password")){
            throw new IllegalArgumentException("이메일과 패스워드를 입력해주세요.");
        }
        ResponseMemberDto responseMemberDto = memberService.login(user.get("email"),user.get("password"));

        return jwtTokenProvider.createToken(responseMemberDto.getUsername(), responseMemberDto.getRoles());
    }
}
