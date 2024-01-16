package com.nexters.dailyphrase.like.domain;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.member.domain.Member;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "likes")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Like extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Phrase phrase;
}
