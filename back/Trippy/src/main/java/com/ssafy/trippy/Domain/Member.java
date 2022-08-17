package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trippy.Dto.Update.UpdateMemberDto;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member extends BaseEntity implements UserDetails{
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    private String name;

    private String password;

    private String email;

    private String phone;

    private int gender;

    private LocalDateTime birth;

    private String img_path;

    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @OneToMany(mappedBy="member", cascade = CascadeType.ALL)
    private List<LikePost> likePosts = new ArrayList<>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    private List<Follow> followers = new ArrayList<>();

    @OneToMany(mappedBy = "following", cascade = CascadeType.ALL)
    private List<Follow> followings = new ArrayList<>();

    @Builder
    public Member(Long id, String name, String password, String email, String phone, int gender, LocalDateTime birth, String img_path, String description, List<Bookmark> bookmarks, List<LikePost> likePosts) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.gender = gender;
        this.birth = birth;
        this.img_path = img_path;
        this.description = description;
        this.bookmarks = bookmarks;
        this.likePosts = likePosts;
    }

    public void update(UpdateMemberDto updateMemberDto){
        this.name = updateMemberDto.getName();
        this.email = updateMemberDto.getEmail();
        this.phone = updateMemberDto.getPhone();
        this.gender = updateMemberDto.getGender();
        this.birth = updateMemberDto.getBirth();
        this.img_path = updateMemberDto.getImg_path();
        this.description = updateMemberDto.getDescription();
    }

    public void updatePw(String pw){
        this.password = pw;
    }

    @Override
    public String getPassword(){
        return this.password;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
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
