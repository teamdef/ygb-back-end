package com.yogeunbang.ygbbackend.member;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import com.yogeunbang.ygbbackend.member.entity.JwtManager;
import com.yogeunbang.ygbbackend.member.entity.Member;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @InjectMocks
    private MemberService memberService;

    @Mock
    private MemberRepo memberRepo;
    @Mock
    private NaverService naverService;
    @Mock
    private JwtManager jwtManager;

    @Test
    public void 로그인_테스트() {

        //given
        Member member = new Member("identity", "nickName", "imgLink", "accessToken");
        given(naverService.requestMember(any()))
            .willReturn(member);
        given(memberRepo.findByIdentity("identity")).willReturn(List.of(member));
        given(jwtManager.makeJwt(member.getIdentity())).willReturn("jwtToken");

        //when
        TokenDto token = memberService.authenticate(new TokenDto("accessToken"));

        //then
        assertThat(token.getAccessToken()).isEqualTo("jwtToken");
    }

    @Test
    public void 회원가입_테스트() {

        //given
        Member member = new Member("identity", "nickName", "imgLink","accessToken");
        given(naverService.requestMember(any())).willReturn(member);
        given(memberRepo.findByIdentity("identity")).willReturn(List.of());
        given(jwtManager.makeJwt(member.getIdentity())).willReturn("jwtToken");

        //when
        TokenDto token = memberService.authenticate(new TokenDto("accessToken"));

        //then
        assertThat(token.getAccessToken()).isEqualTo("jwtToken");
    }

    @Test
    public void 회원탈퇴_테스트() {

        //when
        memberService.unregister(1L);
    }
}
