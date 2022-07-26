package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Location extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="LOCATION_ID")
    private Long id;

    @Column(nullable = false)
    @NotBlank(message="국가이름을 입력하세요")
    private String countryName;

    @Column(nullable = false)
    @NotBlank(message="도시이름을 입력하세요")
    private String cityName;

    @Column(nullable = false)
    @NotBlank(message="경도을 입력하세요")
    private Double latitude;

    @Column(nullable = false)
    @NotBlank(message="위도를 입력하세요")
    private Double longitude;




}
