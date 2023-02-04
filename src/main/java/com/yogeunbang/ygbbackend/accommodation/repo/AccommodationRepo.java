package com.yogeunbang.ygbbackend.accommodation.repo;

import com.yogeunbang.ygbbackend.accommodation.entity.Accommodation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRepo extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findBySpot_Id(Long spot_id);
}
