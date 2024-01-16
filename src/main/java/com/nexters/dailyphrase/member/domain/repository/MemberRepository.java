package com.nexters.dailyphrase.member.domain.repository;

import com.nexters.dailyphrase.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
