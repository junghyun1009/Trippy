package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Badge {
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

    @NotBlank(message="생성일은 필수입니다.")
    private LocalDateTime createdAt;

    @NotBlank(message="수정일은 필수입니다.")
    private LocalDateTime updatedAt;


}
