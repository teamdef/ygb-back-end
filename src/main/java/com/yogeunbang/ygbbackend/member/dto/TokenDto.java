package com.yogeunbang.ygbbackend.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TokenDto {
    private String accessToken;

    public TokenDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
