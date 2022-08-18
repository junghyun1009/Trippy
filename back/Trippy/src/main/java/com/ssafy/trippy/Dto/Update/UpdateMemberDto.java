package com.ssafy.trippy.Dto.Update;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class UpdateMemberDto {

    private String name;
    private String email;
    private String phone;
    private int gender;
    private LocalDateTime birth;
    private String img_path;
    private String description;

    private String img_link;

    public UpdateMemberDto(String name,String email,String phone,int gender,LocalDateTime birth,String img_path,String description,String img_link){
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.img_path = img_path;
        this.description = description;
        this.img_link = img_link;
    }
}
