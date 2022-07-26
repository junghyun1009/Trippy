package com.ssafy.trippy.Service;


import com.ssafy.trippy.Entity.Member;
import com.ssafy.trippy.Service.Impl.MemberServiceImpl;

public interface MemberService {
    Member signup(Member member);
}
