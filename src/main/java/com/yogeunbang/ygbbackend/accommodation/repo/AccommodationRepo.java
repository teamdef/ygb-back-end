package com.yogeunbang.ygbbackend.accommodation.repo;

import com.yogeunbang.ygbbackend.accommodation.dto.AccommodationDto;
import com.yogeunbang.ygbbackend.accommodation.entity.Accommodation;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccommodationRepo extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findBySpot_Id(Long spot_id);

    @Query("select a from Accommodation a where a.flag = ?1")
    Page<Accommodation> findByFlag(int i, Pageable pageable);
}
