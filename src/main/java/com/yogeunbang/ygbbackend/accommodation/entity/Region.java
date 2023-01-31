package com.yogeunbang.ygbbackend.accommodation.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Region {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private Boolean open;
    private String image;
    private String banner;
}
