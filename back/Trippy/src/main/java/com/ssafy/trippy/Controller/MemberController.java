package com.ssafy.trippy.Controller;

import com.ssafy.trippy.Dto.Request.RequestLoginDto;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseBadgeDto;
import com.ssafy.trippy.Dto.Response.ResponseLoginDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import com.ssafy.trippy.Service.BadgeService;
import com.ssafy.trippy.Service.EmailService;
import com.ssafy.trippy.Service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;
    private final BadgeService badgeService;

    /**
     * 회원가입
     */
    @ApiOperation(value = "회원가입")
    @ApiImplicitParam(name = "userData", value = "유저의 정보를 담은 객체")
    @PostMapping("/members/join")
    public ResponseEntity<?> join(@RequestBody @Valid RequestMemberDto requestMemberDto) {
        ResponseMemberDto responseMemberDto = memberService.signup(requestMemberDto);
        if (responseMemberDto.getEmail() == null) {
            return new ResponseEntity<>("이메일 중복", HttpStatus.BAD_REQUEST);
        }
        ResponseBadgeDto responseBadgeDto = badgeService.saveBadge(1L,responseMemberDto.getId());
        responseMemberDto.addBadge(responseBadgeDto);
        return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
    }

    /**
     * 로그인
     */
    @PostMapping("/members/login")
    public ResponseEntity<?> login(@RequestBody @Valid RequestLoginDto user) {
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("이메일 null");
        }
        if (user.getPassword() == null) {
            throw new IllegalArgumentException("패스워드 null");
        }
        ResponseLoginDto responseLoginDto = memberService.login(user.getEmail(), user.getPassword());

        return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
    }

    /**
     * Access Token 재발급
     */
    @GetMapping("/members/re-issue")
    public ResponseEntity<?> reIssue(@RequestParam String email, @RequestParam String refreshToken) {
        ResponseLoginDto responseLoginDto = memberService.reIssueAccessToken(email, refreshToken);
        return new ResponseEntity<>(responseLoginDto, HttpStatus.OK);
    }

    /**
     * 회원 삭제
     */
    @DeleteMapping("/auth/members/remove")
    public ResponseEntity<?> removeMember(HttpServletRequest request) {
        Long id = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        memberService.deleteMember(id);
        return new ResponseEntity<>("회원삭제성공", HttpStatus.OK);
    }

    /**
     * 회원 수정
     */
    @PutMapping("/auth/members/modify")
    public ResponseEntity<?> modifyMember(HttpServletRequest request, @RequestBody UpdateMemberDto updateMemberDto) {
        Long id = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        memberService.updateMember(id, updateMemberDto);
        return new ResponseEntity<>("회원수정성공", HttpStatus.OK);
    }

    /**
     * 회원 정보 받아오기
     */
    @GetMapping("/auth/members")
    public ResponseMemberDto getMember(HttpServletRequest request) {
        Long id = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        return memberService.selectMember(id);
    }

    /**
     * pk값만으로 회원정보 가져오기
     */
    @GetMapping("/members/{memberId}")
    public ResponseEntity<?> getMemberById(@PathVariable("memberId") Long memberId) {
        try {
            ResponseMemberDto responseMemberDto = memberService.selectMember(memberId);
            return new ResponseEntity<>(responseMemberDto, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>("해당 유저 정보를 가져올 수 없습니다",HttpStatus.NOT_FOUND);
        }
    }

    /**
     * 로그아웃
     */
    @GetMapping("members/logout")
    public String logout(HttpServletRequest request) {
        String accessToken = request.getHeader("X-AUTH-TOKEN");
        Long id = memberService.getIdByToken(accessToken);
        memberService.logout(id, accessToken);
        return "로그아웃 완료";
    }

    /**
     * 비밀번호 변경
     */
    @PostMapping("/members/change_pw")
    public String changePw(@RequestBody RequestLoginDto requestLoginDto) {
        memberService.changePw(requestLoginDto);
        return "비밀번호 변경 완료";
    }

    /**
     * 이메일 중복 확인
     */
    @GetMapping("/members/duplicate")
    public boolean chkDup(@RequestParam("email") String email) {
        return memberService.chkDuplicate(email);
    }

    /**
     * 인증 이메일 보내기
     */
    @PostMapping("/members/join/authmail")
    @ApiOperation(value = "회원 가입시 이메인 인증", notes = "기존사용하고 있는 이메일을 통해 인증")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    /**
     * 이메일 확인하기
     */
    public ResponseEntity<?> emailConfirm(
            @RequestBody @ApiParam(value = "이메일정보 정보", required = true) Map<String, String> email) throws Exception {
        String confirm = emailService.sendSimpleMessage(email.get("email"));
        return new ResponseEntity<>(confirm, HttpStatus.OK);
    }

    /**
     * 멤버의 뱃지 가져오기
     */
    @GetMapping("/members/badges")
    public ResponseEntity<?> getBadges(HttpServletRequest request){
        Long memberId = memberService.getIdByToken(request.getHeader("X-AUTH-TOKEN"));
        return new ResponseEntity<>(badgeService.getBadgesByMemberId(memberId),HttpStatus.OK);
    }
}
