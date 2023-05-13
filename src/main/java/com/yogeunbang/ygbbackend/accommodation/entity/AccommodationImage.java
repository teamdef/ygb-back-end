package com.yogeunbang.ygbbackend.accommodation.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
public class AccommodationImage {

    @Id @GeneratedValue
    public long id;
    public String image;
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(name = "accommodation_id")
    public Accommodation accommodation;

    public AccommodationImage(String image, Accommodation accommodation) {
        this.image = image;
        this.accommodation = accommodation;
        accommodation.addImage(this);
    }
}
