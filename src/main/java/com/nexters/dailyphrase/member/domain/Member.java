package com.nexters.dailyphrase.member.domain;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.common.enums.MemberRole;
import com.nexters.dailyphrase.common.enums.SocialType;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private MemberRole role = MemberRole.USER;

    private String socialId;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    private SocialType socialType = SocialType.KAKAO;

    private String profileImgUrl;

    private String email;
}
