package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Entity.PostTransport;
import com.ssafy.trippy.Entity.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {
}