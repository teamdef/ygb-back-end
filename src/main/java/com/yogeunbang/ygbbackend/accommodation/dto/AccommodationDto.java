package com.yogeunbang.ygbbackend.accommodation.dto;

import com.yogeunbang.ygbbackend.accommodation.entity.Accommodation;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class AccommodationDto {

    private Long id;
    private String name;
    private Integer time;
    private Integer price;
    private Integer address;
    private Double lat;
    private Double lng;
    private Integer type;
    private List<String> images;

    public AccommodationDto(Accommodation accommodation) {
        id = accommodation.getId();
        name = accommodation.getName();
        time = accommodation.getTime();
        price = accommodation.getPrice();
        address = accommodation.getAddress();
        lat = accommodation.getLat();
        lng = accommodation.getLng();
        type = accommodation.getCategory();
        images = accommodation.getImages()
            .stream().map(x -> x.image).collect(Collectors.toList());
    }
}
