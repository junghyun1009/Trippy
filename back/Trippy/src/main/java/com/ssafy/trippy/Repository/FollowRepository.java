package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Follow;
import com.ssafy.trippy.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowRepository extends JpaRepository<Follow, Long> {

    void deleteByFollowerIdAndFollowingId(Long followerId,Long followingId);
    boolean existsByFollowerIdAndFollowingId(Long followerId,Long followingId);
    List<Follow> findAllByFollowerId(Long followerId);
    List<Follow> findAllByFollowingId(Long followingId);
    Long countByFollowerId(Long followerId);
    Long countByFollowingId(Long followerId);


}