package com.yogeunbang.ygbbackend.accommodation;

import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/regions")
    public ResponseEntity<List<RegionDto>> findRegions() {
        return ResponseEntity.ok(accommodationService.findRegions());
    }
}
