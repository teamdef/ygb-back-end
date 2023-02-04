package com.yogeunbang.ygbbackend.accommodation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.dto.SpotDto;
import com.yogeunbang.ygbbackend.accommodation.entity.Region;
import com.yogeunbang.ygbbackend.accommodation.entity.Spot;
import com.yogeunbang.ygbbackend.accommodation.repo.RegionRepo;
import com.yogeunbang.ygbbackend.accommodation.repo.SpotRepo;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AccommodationServiceTest {

    @InjectMocks
    private AccommodationService accommodationService;

    @Mock
    private RegionRepo regionRepo;
    @Mock
    private SpotRepo spotRepo;

    @Test
    public void 지역_조회_테스트() {

        //given
        given(regionRepo.findAll())
            .willReturn(List.of(
                Region.builder().name("제주도").build(),
                Region.builder().name("부산").build())
            );

        //when
        List<RegionDto> regions = accommodationService.findRegions();

        //then
        assertThat(regions.get(0).getName()).isEqualTo("제주도");
        assertThat(regions.get(1).getName()).isEqualTo("부산");
    }

    @Test
    public void 관광지_조회_테스트() {

        //given
        given(spotRepo.findByRegion_Id(any(Long.class)))
            .willReturn(List.of(
                Spot.builder().name("성산일출봉").build(),
                Spot.builder().name("중문 관광단지").build())
            );

        //when
        List<SpotDto> spots = accommodationService.findSpots(1L);

        //then
        assertThat(spots.get(0).getName()).isEqualTo("성산일출봉");
        assertThat(spots.get(1).getName()).isEqualTo("중문 관광단지");
    }
}