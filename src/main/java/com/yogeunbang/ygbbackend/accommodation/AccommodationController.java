package com.yogeunbang.ygbbackend.accommodation;

import com.yogeunbang.ygbbackend.accommodation.dto.AccommodationDto;
import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.dto.SpotDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping("/regions")
    public ResponseEntity<List<RegionDto>> findRegions() {
        return ResponseEntity.ok(accommodationService.findRegions());
    }

    @GetMapping("/regions/{region_id}/spots")
    public ResponseEntity<List<SpotDto>> findSpots(@PathVariable Long region_id) {
        return ResponseEntity.ok(accommodationService.findSpots(region_id));
    }

    @GetMapping("/spots/{spot_id}/accommodations")
    public ResponseEntity<List<AccommodationDto>> findAccommodations(@PathVariable Long spot_id) {
        return ResponseEntity.ok(accommodationService.findAccommodations(spot_id));
    }

    @GetMapping("/accommodations")
    public ResponseEntity<List<AccommodationDto>> findCrawlingAccommodations(Pageable pageable) {
        return ResponseEntity.ok(accommodationService.findCrawledAccommodations(pageable));
    }

    @GetMapping("/accommodations/{accommodation_id}")
    public ResponseEntity<AccommodationDto> findAccommodationById(
        @PathVariable("accommodation_id") Long id) {

        return ResponseEntity.ok(accommodationService.findAccommodationById(id));
    }
}
