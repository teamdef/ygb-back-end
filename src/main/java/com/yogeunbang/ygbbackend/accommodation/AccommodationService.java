package com.yogeunbang.ygbbackend.accommodation;

import com.yogeunbang.ygbbackend.accommodation.dto.AccommodationDto;
import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.dto.SpotDto;
import com.yogeunbang.ygbbackend.accommodation.entity.Accommodation;
import com.yogeunbang.ygbbackend.accommodation.repo.AccommodationRepo;
import com.yogeunbang.ygbbackend.accommodation.repo.RegionRepo;
import com.yogeunbang.ygbbackend.accommodation.repo.SpotRepo;
import java.util.List;
import java.util.stream.Collectors;
import com.yogeunbang.ygbbackend.infra.FileProcessService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AccommodationService {

    private final RegionRepo regionRepo;
    private final SpotRepo spotRepo;
    private final AccommodationRepo accommodationRepo;
    private final FileProcessService fileProcessService;

    public List<RegionDto> findRegions() {
        return regionRepo.findAll().stream().map(RegionDto::new).collect(Collectors.toList());
    }

    public List<SpotDto> findSpots(Long region_id) {
        return spotRepo.findByRegion_Id(region_id)
            .stream().map(SpotDto::new).collect(Collectors.toList());
    }

    public List<AccommodationDto> findAccommodations(Long spot_id) {
        return accommodationRepo.findBySpot_Id(spot_id)
            .stream().map(AccommodationDto::new).collect(Collectors.toList());
    }

    public List<AccommodationDto> findCrawledAccommodations(Pageable pageable) {
        return accommodationRepo.findByFlag(0, pageable)
            .stream().map(AccommodationDto::new).collect(Collectors.toList());
    }

    public AccommodationDto findAccommodationById(Long id) {
        return new AccommodationDto(accommodationRepo.getReferenceById(id));
    }

    @Transactional
    public void enrollAccommodation(AccommodationDto accommodationDto, List<MultipartFile> images) {
        List<String> imgUrls = fileProcessService.uploadMultiImage(images);
        Accommodation accommodation = accommodationRepo.getReferenceById(accommodationDto.getId());
        fileProcessService.mappingImages(imgUrls, accommodation);
        accommodation.enroll(accommodationDto);
    }
}
