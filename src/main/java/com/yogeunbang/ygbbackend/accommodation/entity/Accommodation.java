package com.yogeunbang.ygbbackend.accommodation.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
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
    private Integer address;
    private Integer category;
    private String link;
    private String homePage;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "spot_id")
    private Spot spot;
    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationImage> images = new ArrayList<>();

    @Builder
    public Accommodation(String name, Integer time, Integer price, Integer checkIn,
        Integer checkOut, Integer address, Integer category,
        String link, Spot spot) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.address = address;
        this.category = category;
        this.link = link;
        this.spot = spot;
    }

    @Builder
    public Accommodation(String name, Integer time, Integer price, Integer checkIn,
        Integer checkOut, Integer address, Integer category, Double lat, Double lng,
        String link, Spot spot) {
        this.name = name;
        this.time = time;
        this.price = price;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.lat = lat;
        this.lng = lng;
        this.address = address;
        this.category = category;
        this.link = link;
        this.spot = spot;
    }
}
