package com.nexters.dailyphrase.share.domain;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import jakarta.persistence.*;
import lombok.*;

// Note - 로그성 데이터로 직접 참조를 하지 않고 간접 참조를 합니다.
@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Share extends BaseDateTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private Long phraseId;
}
