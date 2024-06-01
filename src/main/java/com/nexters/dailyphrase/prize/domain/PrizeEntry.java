package com.nexters.dailyphrase.prize.domain;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.common.enums.PrizeEntryStatus;

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
    @JoinColumn(name = "prize_id", nullable = false)
    private Prize prize;

    @Enumerated(EnumType.STRING)
    private PrizeEntryStatus status;
}
