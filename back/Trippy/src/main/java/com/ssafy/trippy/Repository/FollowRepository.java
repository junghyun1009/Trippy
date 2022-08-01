//package com.ssafy.trippy.Repository;
//
//import com.ssafy.trippy.Entity.Follow;
//import com.ssafy.trippy.Entity.Member;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//
//@Repository
//public interface FollowRepository extends JpaRepository<Follow, Long> {
//
//    Follow findFollowByFollowingAndFollower(Member following, Member follower);
//    List<Follow> findAllByFollowingAndMember(Member member, Member following);
//    List<Follow> findAllByFollowerAndMember(Member member, Member follower);
//    @Modifying
//    @Query(value = "INSERT INTO follow(following, follower) VALUES(:fromId, :toId)", nativeQuery = true)
//    void follow(long fromId, long toId);
//
//    @Modifying
//    @Query(value = "DELETE FROM follow WHERE following = :fromId AND follower = :toId", nativeQuery = true)
//    void unFollow(long fromId, long toId);
//
//}