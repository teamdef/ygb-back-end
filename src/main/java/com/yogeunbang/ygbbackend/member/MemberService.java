package com.yogeunbang.ygbbackend.member;

import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import com.yogeunbang.ygbbackend.member.entity.JwtManager;
import com.yogeunbang.ygbbackend.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepo memberRepo;
    private final NaverService naverService;
    private final JwtManager jwtManager;

    @Transactional
    public TokenDto authenticate(TokenDto token) {
        Member member = naverService.requestMember(token);

        if (memberRepo.findByIdentity(member.getIdentity()).size() == 0){ memberRepo.save(member);}
        else {member.updateAccessToken(token.getAccessToken());}

        return new TokenDto(jwtManager.makeJwt(member.getIdentity()));
    }

    @Transactional
    public void unregister(Long memberId) {
        Member member = memberRepo.getReferenceById(memberId);
        naverService.unregister(member);
    }

}
