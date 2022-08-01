package com.ssafy.trippy.Repository;

import com.ssafy.trippy.Domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByCityNameAndCountryName(String cityName, String countryName);
}