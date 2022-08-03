package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestMemberDto {
    private Long id;
    private String name;
    private String password;
    private String email;
    private String phone;
    private int gender;
    private LocalDateTime birth;
    private String img_path;
    private String desc;

    // DTO -> ENTITY
    public Member toEntity(){
        return Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .email(email)
                .phone(phone)
                .gender(gender)
                .birth(birth)
                .img_path(img_path)
                .desc(desc)
                .build();
    }

    public void encodePassword(String encodedPassword){
        this.password = encodedPassword;
    }
}
