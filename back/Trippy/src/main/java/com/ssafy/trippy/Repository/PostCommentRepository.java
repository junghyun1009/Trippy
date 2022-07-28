package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.MemberBadge;
import com.ssafy.trippy.Entity.PostComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
}