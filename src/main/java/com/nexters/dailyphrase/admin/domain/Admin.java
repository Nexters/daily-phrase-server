package com.nexters.dailyphrase.admin.domain;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.common.enums.MemberRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Admin extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String userId;

    private String password;

    @Enumerated(EnumType.STRING)
    private MemberRole role;
}
