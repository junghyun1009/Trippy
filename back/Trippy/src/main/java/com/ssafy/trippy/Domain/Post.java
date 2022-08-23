package com.ssafy.trippy.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
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


    private int company;

    private int count;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String memberImg;

    @Column(name="REPRESENTIVE_IMG")
    private int representiveImg;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy="post", cascade = CascadeType.REMOVE)
    private List<Route> routes = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private List<PostTransport> postTransports = new ArrayList<>();

    @OneToMany(mappedBy="post", cascade = CascadeType.REMOVE)
    private List<DetailLocation> detailLocations = new ArrayList<>();

    @OneToMany(mappedBy="post", cascade = CascadeType.REMOVE)
    private List<PostComment> postComments = new ArrayList<>();

    @OneToMany(mappedBy="post", cascade = CascadeType.REMOVE)
    private List<LikePost> likePosts = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;


    @Builder
    public Post(Long id, String title, int company, int count, LocalDateTime startDate, LocalDateTime endDate, int representiveImg, Member member, List<PostTransport> postTransports, List<DetailLocation> detailLocations, List<PostComment> postComments, List<Route> routes,Location location, String memberImg) {
        this.id = id;
        this.title = title;
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
        this.location = location;
        this.memberImg = memberImg;
    }

    public void update(String title, int company, int count, LocalDateTime startDate, LocalDateTime endDate, List<PostTransport> postTransports, List<DetailLocation> detailLocations,List<Route> routes, Location location){
        this.title = title;
        this.company = company;
        this.count = count;
        this.startDate = startDate;
        this.endDate = endDate;
        this.postTransports = postTransports;
        this.detailLocations = detailLocations;
        this.routes = routes;
        this.location = location;
    }
}
