package com.yogeunbang.ygbbackend.accommodation.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Spot {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private Boolean car;
    private String image;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "region_id")
    private Region region;

    @Builder
    public Spot(String name, String description, Boolean car, String image,
        Region region) {
        this.name = name;
        this.description = description;
        this.car = car;
        this.image = image;
        this.region = region;
    }
}
