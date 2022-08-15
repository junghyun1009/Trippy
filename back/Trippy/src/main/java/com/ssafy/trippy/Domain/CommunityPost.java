package com.ssafy.trippy.Domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommunityPost extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "COMMUNITY_POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private int category;

    @Column(nullable = false)
    private LocalDateTime meetingTime;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int recruitVolume;

    @Column(nullable = false)
    private int recruitCurrentVolume;

    @Column(nullable = false)
    private int startAge;

    @Column(nullable = false)
    private int endAge;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false, name="IS_LOCAL")
    private boolean Local;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false, name="IS_DAY")
    private boolean Day;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LOCATION_ID")
    private Location location;

    @OneToMany(mappedBy = "communityPost", cascade = CascadeType.ALL)
    private List<Bookmark> bookmarks = new ArrayList<>();

    @Builder
    public CommunityPost(Long id, String title, String description, int category, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, String gender, boolean Local, Member member, Location location, String place, boolean Day) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.Local = Local;
        this.member = member;
        this.location = location;
        this.place = place;
        this.Day = Day;
    }

    // update문
    public void update(String title, String description, int category, LocalDateTime meetingTime, LocalDateTime startDate, LocalDateTime endDate, int recruitVolume, int recruitCurrentVolume, int startAge, int endAge, String gender, boolean Local, Location location, String place, boolean Day) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.meetingTime = meetingTime;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruitVolume = recruitVolume;
        this.recruitCurrentVolume = recruitCurrentVolume;
        this.startAge = startAge;
        this.endAge = endAge;
        this.gender = gender;
        this.Local = Local;
        this.location = location;
        this.place = place;
        this.Day = Day;
    }

}
