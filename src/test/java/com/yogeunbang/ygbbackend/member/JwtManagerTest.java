package com.yogeunbang.ygbbackend.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.yogeunbang.ygbbackend.member.entity.JwtManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class JwtManagerTest {

    @InjectMocks
    JwtManager jwtManager;

    @BeforeEach
    public void setUp() {
        ReflectionTestUtils.setField(jwtManager, "key", "testKey");
        ReflectionTestUtils.setField(jwtManager, "time", "2000");
    }

    @Test
    void Jwt_생성_테스트() {

        //given
        String memberId = "memberId";

        //when & then
        assertThat(jwtManager.makeJwt(memberId)).isInstanceOf(String.class);
    }

    @Test
    void 유효하지_않은_Jwt_확인_테스트() {

        // given
        String jwt = "invalidJwt";
        ReflectionTestUtils.setField(jwtManager, "time", "-1");
        String memberId = "memberId";
        String jwt2 = jwtManager.makeJwt(memberId);


        //when & then
        // 유효하지 않은 토큰 값일때
        assertThrows(RuntimeException.class, () -> {
            jwtManager.validateToken(jwt);
        });

        // 토큰이 만료된 경우
        assertThrows(RuntimeException.class, () -> {
            jwtManager.validateToken(jwt2);
        });
    }

}
