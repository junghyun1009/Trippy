package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Badge extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name="BADGE_ID")
    private Long id;

    @Column(nullable = false,name="BADGE_NAME")
    @NotBlank(message="뱃지 이름은 필수입니다.")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "뱃지 설명은 필수입니다.")
    private String desc;




}