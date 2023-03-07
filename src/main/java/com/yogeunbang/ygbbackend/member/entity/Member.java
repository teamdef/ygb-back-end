package com.yogeunbang.ygbbackend.member.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Member {
    @Id @GeneratedValue
    private Long id;
    private String identity;
    private String nickname;
    private String profileImage;

    public Member(String identity, String nickname, String profileImage) {
        this.identity = identity;
        this.nickname = nickname;
        this.profileImage = profileImage;
    }
}
