package com.ssafy.trippy.Dto.Request;

import com.ssafy.trippy.Domain.Member;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RequestMemberDto {

    @ApiParam(value = "사용자 이름")
    @NotEmpty(message="이름은 빈값일 수 없습니다.")
    private String name;

    @ApiParam(value = "비밀번호")
    @NotEmpty(message="비밀번호는 빈값일 수 없습니다.")
    @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}",
            message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8자 ~ 20자의 비밀번호여야 합니다.")
    @Size(min = 8,max = 20)
    private String password;
    @Email
    @ApiParam(value = "이메일")
    @NotEmpty(message="이메일은 빈값일 수 없습니다.")
    private String email;
    @ApiParam(value = "전화번호")
    @Pattern(regexp = "^01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$", message = "10 ~ 11 자리의 숫자만 입력 가능합니다.")
    @NotEmpty(message="핸드폰 번호는 빈값일 수 없습니다.")
    private String phone;
    @ApiParam(value = "성별")
    @NotNull(message="성별은 빈값일 수 없습니다.")
    private int gender;
    @ApiParam(value = "생일")
    @NotNull(message="성별은 빈값일 수 없습니다.")
    private LocalDateTime birth;
    @ApiParam(value = "프로필 이미지 경로")
    private String img_path;
    @ApiParam(value = "설명")
    private String description;

    // DTO -> ENTITY
    public Member toEntity(){
        return Member.builder()
                .name(name)
                .password(password)
                .email(email)
                .phone(phone)
                .gender(gender)
                .birth(birth)
                .img_path(img_path)
                .description(description)
                .build();
    }

    public void encodePassword(String encodedPassword){
        this.password = encodedPassword;
    }
}
