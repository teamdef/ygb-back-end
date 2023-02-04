package com.yogeunbang.ygbbackend.accommodation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.entity.Region;
import com.yogeunbang.ygbbackend.accommodation.repo.RegionRepo;
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
}