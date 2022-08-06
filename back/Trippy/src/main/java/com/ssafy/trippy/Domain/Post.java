package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
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

    private String title;

//    @Column(name="IS_DELETE",nullable = false)
//    @NotBlank(message="삭제 여부를 입력하세요")
    private Byte isDelete;

//    @Column(nullable = false)
//    @NotBlank(message="일행타입을 입력하세요")
    private int company;

//    @Column(nullable = false)
//    @NotBlank(message="일행수를 입력하세요")
    private int count;

//    @Column(nullable = false)
//    @NotBlank(message="시작날짜를 입력하세요")
    private LocalDateTime startDate;

//    @Column(nullable = false)
//    @NotBlank(message="끝나는 날짜를 입력하세요")
    private LocalDateTime endDate;

//    @Column(name="REPRESENTIVE_IMG",nullable = false)
//    @NotBlank(message="대표이미지를 입력하세요")
    private int representiveImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy="post")
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "post" )
    private List<PostTransport> postTransports = new ArrayList<>();

    @OneToMany(mappedBy="post", cascade = CascadeType.REMOVE)
    private List<DetailLocation> detailLocations = new ArrayList<>();

    @OneToMany(mappedBy="post")
    private List<PostComment> postComments = new ArrayList<>();


    @Builder
    public Post(Long id, String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, Member member, List<PostTransport> postTransports, List<DetailLocation> detailLocations, List<PostComment> postComments, List<Route> routes) {
        this.id = id;
        this.title = title;
        this.isDelete = isDelete;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representiveImg = representiveImg;
        this.member = member;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
        this.postComments = postComments;
        this.routes = routes;
    }

    public void update(String title, Byte isDelete, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, List<PostTransport> postTransports, List<DetailLocation> detailLocations,List<Route> routes){
        this.title = title;
        this.isDelete = isDelete;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.representiveImg = representiveImg;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
        this.routes = routes;
    }
}
