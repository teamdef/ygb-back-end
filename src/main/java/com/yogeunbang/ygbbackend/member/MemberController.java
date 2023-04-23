package com.yogeunbang.ygbbackend.member;

import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/members")
    public TokenDto authenticate(@RequestBody TokenDto token) {
        System.out.println(token);
        return memberService.authenticate(token);
    }

    @DeleteMapping("/members")
    public void unregister(@RequestHeader(value = "Authorization") String accessToken) {
        memberService.unregister(accessToken);
    }

    @GetMapping("/members")
    public MemberDto getMember(@RequestHeader(value = "Authorization") String accessToken) {
        return new MemberDto(memberService.getMember(accessToken));
    }

    @PatchMapping("/members")
    public void updateMember(
        @RequestHeader(value = "Authorization") String accessToken,
        @RequestParam("nickname") String nickName,
        @RequestParam("image") MultipartFile image
    ) {
        memberService.updateMember(accessToken, nickName, image);
    }
}
