package com.ssafy.trippy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    @Length(min=1, max=20,message="이름 사이즈를 확인하세요")
    @NotBlank(message="이름은 필수값입니다.")
    private String name;

//    @JsonIgnore
    @Column(nullable = false, length=15)
    @NotBlank(message="비밀번호는 필수값입니다.")
    private String password;

    @Column(nullable = false, length=60)
    @Length(min=1, max=50,message="이메일 사이즈를 확인하세요")
    @NotBlank(message="이메일은 필수값입니다.")
    private String email;

    @Column(nullable = false, length=15)
    @NotBlank(message="핸드폰 번호는 필수값입니다.")
    private String phone;

    @Column(nullable = false, length=2)
    @NotBlank(message="성별은 필수값입니다.")
    private int gender;

    @Column(nullable = false, length=15)
    @NotBlank(message="생일은 필수값입니다.")
    private LocalDateTime birth;

//    @JsonIgnore
    private String img_path;

//    @JsonIgnore
    private String desc;


    @OneToMany(mappedBy="member")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy="member")
    private List<LikePost> likePosts = new ArrayList<>();




}
