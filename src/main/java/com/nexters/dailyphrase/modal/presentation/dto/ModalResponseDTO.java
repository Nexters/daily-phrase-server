package com.nexters.dailyphrase.modal.presentation.dto;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.enums.ModalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ModalResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ModalDetail {
        private Long id;
        private ModalType type;
        private String imageUrl;
        private String leftButtonText;
        private String rightButtonText;
    }
}
