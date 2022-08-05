package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Post;
import com.ssafy.trippy.Domain.PostTransport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTransportRepository extends JpaRepository<PostTransport, Long> {
    List<PostTransport> findAllPostTransportByPost(Post post);
}