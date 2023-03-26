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
    private String accessToken;

    public Member(String identity, String nickname, String profileImage, String accessToken) {
        this.identity = identity;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.accessToken = accessToken;
    }

    public void updateAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
