package com.yogeunbang.ygbbackend.accommodation.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Accommodation {

    @Id @GeneratedValue
    private Long id;
    private String name;
    private Integer time;
    private Integer price;
    private Integer checkIn;
    private Integer checkOut;
    private Double lat;
    private Double lng;
    private Integer category;
    private String link;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "spot_id")
    private Spot spot;
}
