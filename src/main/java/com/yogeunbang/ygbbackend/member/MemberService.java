package com.yogeunbang.ygbbackend.member;

import com.yogeunbang.ygbbackend.member.dto.TokenDto;
import com.yogeunbang.ygbbackend.member.entity.JwtManager;
import com.yogeunbang.ygbbackend.member.entity.Member;
import java.util.List;
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
        Member requestMember = naverService.requestMember(token);
        List<Member> members = memberRepo.findByIdentity(requestMember.getIdentity());

        if (members.size() == 0){
            memberRepo.save(requestMember);

        } else if (members.size() > 0) {
            Member member = members.get(0);
            member.updateAccessToken(token.getAccessToken());
        }

        return new TokenDto(jwtManager.makeJwt(requestMember.getIdentity()));
    }

    @Transactional
    public void unregister(String accessToken) {
        Member member = getMember(accessToken);
        naverService.unregister(member);
        memberRepo.delete(member);
    }

    public Member getMember(String accessToken) {
        String memberId = jwtManager.getMemberId(accessToken);
        return memberRepo.findByIdentity(memberId).get(0);
    }
}
