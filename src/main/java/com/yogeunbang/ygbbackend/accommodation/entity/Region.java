package com.yogeunbang.ygbbackend.accommodation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Region {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private Boolean open;
    private String image;
    private String banner;

    @Builder
    public Region(String name, Boolean open, String image, String banner) {
        this.id = id;
        this.name = name;
        this.open = open;
        this.image = image;
        this.banner = banner;
    }
}
