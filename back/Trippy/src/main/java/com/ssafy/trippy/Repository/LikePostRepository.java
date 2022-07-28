package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.LikePost;
import com.ssafy.trippy.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
}