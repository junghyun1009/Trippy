package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Transport extends BaseEntity{
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="운송수단을 입력하세요")
    private String name;

}
