package com.yogeunbang.ygbbackend.accommodation;

import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.repo.RegionRepo;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final RegionRepo regionRepo;

    public List<RegionDto> findRegions() {
        return regionRepo.findAll().stream().map(RegionDto::new).collect(Collectors.toList());
    }

}
