package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.Throw;
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
    public String join(@RequestBody Member member){
        memberService.signup(member);
        return member.toString();
    }

    // 로그인
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> user) {
        log.info("user email = {}", user.get("email"));
        if(!user.containsKey("email")){
            throw new IllegalArgumentException("가입되지 않은 E-MAIL 입니다.");
        }
        Member member = memberService.findByEmail(user.get("email"));

        return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
    }
}
