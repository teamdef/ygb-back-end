package com.yogeunbang.ygbbackend.member;

import com.yogeunbang.ygbbackend.member.entity.Member;
import lombok.Getter;

@Getter
public class MemberDto {

    private Long id;
    private String nickname;
    private String profileImage;

    public MemberDto(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.profileImage = member.getProfileImage();
    }
}
