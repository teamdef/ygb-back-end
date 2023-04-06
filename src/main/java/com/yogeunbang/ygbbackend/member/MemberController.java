package com.yogeunbang.ygbbackend.member;

import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public TokenDto authenticate(@RequestBody TokenDto token) {
        System.out.println(token);
        return memberService.authenticate(token);
    }

    @DeleteMapping("/members/{id}")
    public void unregister(@PathVariable Long id) {
        memberService.unregister(id);
    }

    @GetMapping("/members")
    public MemberDto getMember(@RequestHeader(value = "Authorization") String accessToken) {
        return new MemberDto(memberService.getMember(accessToken));
    }
}
