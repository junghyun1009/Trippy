package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String username);
    Optional<Member> findByEmail(String email);
    boolean existsByEmail(String email);

}
