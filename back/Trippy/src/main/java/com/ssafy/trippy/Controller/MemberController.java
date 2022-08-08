package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Config.JwtProvider;
import com.ssafy.trippy.Dto.Request.RequestLoginDto;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Service.MailService;
import com.ssafy.trippy.Service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final MailService mailService;


    // 회원가입
    @ApiOperation(value = "회원가입")
    @ApiImplicitParam(name = "userData", value = "유저의 정보를 담은 객체")
    @PostMapping("/members/join")
    public ResponseEntity<ResponseMemberDto> join(@RequestBody RequestMemberDto requestMemberDto){
        ResponseMemberDto responseMemberDto = memberService.signup(requestMemberDto);
        return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/members/login")
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
    @GetMapping("/members/re-issue")
    public ResponseEntity<ResponseLoginDto> reIssue(@RequestParam String email, @RequestParam String refreshToken) {
        ResponseLoginDto responseLoginDto = memberService.reIssueAccessToken(email, refreshToken);
        return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
    }

    // 회원 삭제
    @DeleteMapping("/auth/members/remove")
    public ResponseEntity<?> removeMember(HttpServletRequest request){
        Long id = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        memberService.deleteMember(id);
        return new ResponseEntity<>("회원삭제성공", HttpStatus.OK);
    }

    // 회원 수정
    @PutMapping("/auth/members/modify")
    public ResponseEntity<?> modifyMember(HttpServletRequest request, @RequestBody UpdateMemberDto updateMemberDto){
        Long id = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        memberService.updateMember(id,updateMemberDto);
        return new ResponseEntity<>("회원수정성공", HttpStatus.OK);
    }

    // 회원 정보 받아오기
    @GetMapping("/auth/members")
    public ResponseMemberDto getMember(HttpServletRequest request){
        Long id = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        return memberService.selectMember(id);
    }

    // 로그아웃
    @GetMapping("members/logout")
    public String logout(HttpServletRequest request) {
        String accessToken = request.getHeader("X-AUTH-TOKEN");
        Long id = memberService.getIdByToken(accessToken);
        memberService.logout(id, accessToken);
        return "로그아웃 완료";
    }

    // 비밀번호 변경
    @PostMapping("/members/change_pw")
    public String changePw(@RequestBody RequestLoginDto requestLoginDto){
        memberService.changePw(requestLoginDto);
        return "비밀번호 변경 완료";
    }
    // 인증 메일 보내기(현재 사용 불가)
    @PostMapping("/members/join/authmail")
    public String sendAuthMail(@RequestBody Map<String, String> user){
        String regExp = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$";
        String email = user.get("email");
        if(!user.containsKey("email") || email=="" || !email.matches(regExp)){
            throw new IllegalArgumentException("올바른 이메일을 입력해주세요.");
        }
        return mailService.mailCheck(email);
    }
}
