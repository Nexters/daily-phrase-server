package com.nexters.dailyphrase.prize.domain;

import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;
import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PrizeTicket extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @Enumerated(EnumType.STRING)
    private PrizeTicketStatus status;
}
