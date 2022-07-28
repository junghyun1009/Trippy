package com.ssafy.trippy.Dto.Update;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateMemberDto {

    private String name;
    private String password;
    private String email;
    private String phone;
    private int gender;
    private LocalDateTime birth;
    private String img_path;
    private String desc;

    public UpdateMemberDto(String name,String password,String email,String phone,int gender,LocalDateTime birth,String img_path,String desc){
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.img_path = img_path;
        this.desc = desc;
    }
}
