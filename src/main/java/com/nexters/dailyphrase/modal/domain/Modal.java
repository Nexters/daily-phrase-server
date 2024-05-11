package com.nexters.dailyphrase.modal.domain;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.enums.ModalType;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Modal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ModalType type;

    @Column(nullable = false)
    private String imageUrl;

    @Column(nullable = false)
    private String leftButtonText;

    @Column(nullable = false)
    private String rightButtonText;
}
