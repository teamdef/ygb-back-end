package com.yogeunbang.ygbbackend.accommodation.dto;

import com.yogeunbang.ygbbackend.accommodation.entity.Region;
import lombok.Getter;

@Getter
public class RegionDto {
    private Long id;
    private String name;
    private Boolean open;
    private String image;

    public RegionDto(Region region) {
        id = region.getId();
        name = region.getName();
        open = region.getOpen();
        image = region.getImage();
    }
}
