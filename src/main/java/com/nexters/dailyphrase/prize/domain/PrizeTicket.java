package com.nexters.dailyphrase.prize.domain;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.common.enums.PrizeTicketSource;
import com.nexters.dailyphrase.common.enums.PrizeTicketStatus;

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

    private Long eventId;

    @Enumerated(EnumType.STRING)
    private PrizeTicketStatus status;

    @Enumerated(EnumType.STRING)
    private PrizeTicketSource source;

    public void setStatus(PrizeTicketStatus status) {
        this.status = status;
    }
}
