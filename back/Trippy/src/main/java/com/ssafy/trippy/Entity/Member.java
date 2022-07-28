package com.ssafy.trippy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(nullable = false)
    @Length(min=1, max=20,message="이름 사이즈를 확인하세요")
    @NotBlank(message="이름은 필수값입니다.")
    private String name;

//    @JsonIgnore
    @Column(nullable = false)
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
    @NotNull
    private int gender;

    @Column(nullable = false, length=15)
    @NotNull
    private LocalDateTime birth;

//    @JsonIgnore
    private String img_path;

//    @JsonIgnore
    private String desc;


    private String role;

    @OneToMany(mappedBy="member")
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy="member")
    private List<LikePost> likePosts = new ArrayList<>();

    public void encodePassword(String encodedPassword){
        this.password = encodedPassword;
    }

    @Override
    public String getPassword(){
        return this.password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority(this.role));
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public String getUsername() {
        return this.email;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public boolean isEnabled() {
        return true;
    }
}
