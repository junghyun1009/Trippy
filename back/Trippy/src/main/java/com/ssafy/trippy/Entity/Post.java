package com.ssafy.trippy.Entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Post extends BaseEntity{
    @Id
    @GeneratedValue
    @Column(name="POST_ID")
    private Long id;

    @Column(name="IS_DELETE",nullable = false)
    @NotBlank(message="삭제 여부를 입력하세요")
    private Byte isDelete;

    @Column(nullable = false)
    @NotBlank(message="일행타입을 입력하세요")
    private int company;

    @Column(nullable = false)
    @NotBlank(message="일행수를 입력하세요")
    private int count;

    @Column(nullable = false)
    @NotBlank(message="시작날짜를 입력하세요")
    private LocalDateTime startDate;

    @Column(nullable = false)
    @NotBlank(message="끝나는 날짜를 입력하세요")
    private LocalDateTime endDate;

    @Column(name="REPRESENTIVE_IMG",nullable = false)
    @NotBlank(message="대표이미지를 입력하세요")
    private int representiveImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "transport" )
    private List<PostTransport> postTransports = new ArrayList<>();

    @OneToMany(mappedBy="post")
    private List<DetailLocation> detailLocations = new ArrayList<>();


}
