package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.Member;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByName(String username);
    Optional<Member> findByEmail(String email);

}
