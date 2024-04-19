package com.nexters.dailyphrase.readhistory.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;

import lombok.*;

// NOTE - 로그성 데이터라서 member와 phrase는 간접 참조합니다.
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReadHistory extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long phraseId;

    private String userAgent;
}
