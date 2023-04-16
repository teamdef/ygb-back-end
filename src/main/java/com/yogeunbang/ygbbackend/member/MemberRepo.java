package com.yogeunbang.ygbbackend.member;

import com.yogeunbang.ygbbackend.member.entity.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepo extends JpaRepository<Member, Long> {

    public List<Member> findByIdentity(String identity);
}
