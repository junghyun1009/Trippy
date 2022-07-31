package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Dto.Request.RequestLoginDto;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Service.MailService;
import com.ssafy.trippy.Service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;

    // 회원가입
    @PostMapping("/join")
    public Long join(@RequestBody RequestMemberDto requestMemberDto){
        ResponseMemberDto responseMemberDto = memberService.signup(requestMemberDto);
        return responseMemberDto.getId();
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<ResponseLoginDto> login(@RequestBody RequestLoginDto user) {
        if(user.getEmail() == null){
            throw new IllegalArgumentException("이메일 null");
        }
        if(user.getPassword() == null){
            throw new IllegalArgumentException("패스워드 null");
        }
        ResponseLoginDto responseLoginDto = memberService.login(user.getEmail(),user.getPassword());

        return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
    }

    // Access-Token 재발급
    @GetMapping("/re-issue")
    public ResponseEntity<ResponseLoginDto> reIssue(@RequestParam String email, @RequestParam String refreshToken) {
        ResponseLoginDto responseLoginDto = memberService.reIssueAccessToken(email, refreshToken);
        return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/api/remove/{id}")
    public void removeMember(@PathVariable Long id){
        memberService.deleteMember(id);
    }

    // 회원 수정
    @PutMapping("/api/modify/{id}")
    public void modifyMember(@PathVariable Long id, @RequestBody UpdateMemberDto updateMemberDto){
        memberService.updateMember(id,updateMemberDto);
    }

    // 회원 정보 받아오기
    @GetMapping("/api/{id}")
    public ResponseMemberDto getMember(@PathVariable Long id){
        return memberService.selectMember(id);
    }


    @GetMapping("/logout/{id}")
    public String logout(@PathVariable Long id,@RequestParam String accessToken) {
        memberService.logout(id, accessToken);
        return "로그아웃 완료";
    }

    // 인증 메일 보내기(현재 사용 불가)
    @PostMapping("/join/authmail")
    public String sendAuthMail(@RequestBody Map<String, String> user){
        String regExp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        String email = user.get("email");
        if(!user.containsKey("email") || email=="" || !email.matches(regExp)){
            throw new IllegalArgumentException("올바른 이메일을 입력해주세요.");
        }
        return mailService.mailCheck(email);
    }
}
