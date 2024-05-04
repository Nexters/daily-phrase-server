package com.nexters.dailyphrase.prize.domain;

import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;
import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PrizeEntry extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gift_id", nullable = false)
    private Prize gift;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id", nullable = false)
    private PrizeEvent event;

    @Enumerated(EnumType.STRING)
    private PrizeEntryStatus status;
}
