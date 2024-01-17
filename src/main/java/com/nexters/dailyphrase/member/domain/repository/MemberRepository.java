package com.nexters.dailyphrase.member.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.member.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {}
