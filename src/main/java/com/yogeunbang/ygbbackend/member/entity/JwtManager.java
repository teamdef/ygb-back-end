package com.yogeunbang.ygbbackend.member.entity;

import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.time.Duration;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JwtManager {

    @Value("${key.token}")
    private String key;
    @Value("${valid-time}")
    private String time;

    public String makeJwt(String memberId) {
        Date now = new Date();

        //accessToken 생성 및 반환
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setIssuedAt(now)
            .setExpiration(
                new Date(now.getTime() + Duration.ofMinutes(Integer.valueOf(time)).toMillis()))
            .claim("id", memberId)
            .signWith(SignatureAlgorithm.HS256, key)
            .compact();

    }

    public boolean validateToken(String jwtToken) {
        try {
            Jwts.parser().setSigningKey(key).parseClaimsJws(jwtToken);
            return true;
        } catch (JwtException e) {
            log.info(e.toString());
        }
        return false;
    }

}
