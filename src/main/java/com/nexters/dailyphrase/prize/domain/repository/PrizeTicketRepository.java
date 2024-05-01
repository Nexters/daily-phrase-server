package com.nexters.dailyphrase.prize.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.prize.domain.PrizeTicket;

public interface PrizeTicketRepository extends JpaRepository<PrizeTicket, Long> {}
