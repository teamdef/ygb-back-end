package com.yogeunbang.ygbbackend.accommodation;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.yogeunbang.ygbbackend.accommodation.dto.RegionDto;
import com.yogeunbang.ygbbackend.accommodation.entity.Region;
import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AccommodationController.class)
public class AccommodationControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccommodationService accommodationService;

    @Test
    public void 지역_조회_테스트() throws Exception{

        //given
        given(accommodationService.findRegions())
            .willReturn(Arrays.asList(new RegionDto(Region.builder()
                .id(1L)
                .name("제주도")
                .open(Boolean.TRUE)
                .banner("배너이미지 경로")
                .image("이미지 경로")
                .build())));

        //when & then
        mvc.perform(get("/regions"))
            .andExpect(status().isOk())
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.[0].id").value("1"))
            .andExpect(jsonPath("$.[0].name").value("제주도"));

    }

}
