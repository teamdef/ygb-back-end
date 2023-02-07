package com.yogeunbang.ygbbackend.accommodation.repo;

import com.yogeunbang.ygbbackend.accommodation.entity.Spot;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpotRepo extends JpaRepository<Spot, Long> {

    List<Spot> findByRegion_Id(Long region_id);
}
