package com.yogeunbang.ygbbackend.accommodation;

import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.dto.SpotDto;
import com.yogeunbang.ygbbackend.accommodation.repo.RegionRepo;
import com.yogeunbang.ygbbackend.accommodation.repo.SpotRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final RegionRepo regionRepo;
    private final SpotRepo spotRepo;

    public List<RegionDto> findRegions() {
        return regionRepo.findAll().stream().map(RegionDto::new).collect(Collectors.toList());
    }

    public List<SpotDto> findSpots(Long region_id) {
        return spotRepo.findByRegion_Id(region_id)
            .stream().map(SpotDto::new).collect(Collectors.toList());
    }
}
