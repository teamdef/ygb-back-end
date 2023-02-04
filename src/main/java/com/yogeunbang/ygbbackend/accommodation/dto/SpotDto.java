package com.yogeunbang.ygbbackend.accommodation.dto;

import com.yogeunbang.ygbbackend.accommodation.entity.Spot;
import lombok.Getter;

@Getter
public class SpotDto {

    private Long id;
    private String name;
    private String description;
    private Boolean car;
    private String image;

    public SpotDto(Spot spot) {
        id = spot.getId();
        name = spot.getName();
        description = spot.getDescription();
        car = spot.getCar();
        image = spot.getImage();
    }
}
