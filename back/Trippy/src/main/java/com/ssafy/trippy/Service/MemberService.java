package com.ssafy.trippy.Service;

import com.ssafy.trippy.Domain.Member;
import com.ssafy.trippy.Dto.Request.RequestMemberDto;
import com.ssafy.trippy.Dto.Response.ResponseMemberDto;

public interface MemberService {

    ResponseMemberDto signup(RequestMemberDto requestMemberDto);

    ResponseMemberDto login(String email,String password);

    public boolean chkDuplicate(String email);
}
