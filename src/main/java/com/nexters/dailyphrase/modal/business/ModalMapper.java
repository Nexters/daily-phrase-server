package com.nexters.dailyphrase.modal.business;

import java.util.List;
import java.util.stream.Collectors;

import com.nexters.dailyphrase.common.annotation.Mapper;
import com.nexters.dailyphrase.modal.domain.Modal;
import com.nexters.dailyphrase.modal.presentation.dto.ModalResponseDTO;

@Mapper
public class ModalMapper {

    public ModalResponseDTO.ModalDetail toModalDetail(Modal modal) {
        return ModalResponseDTO.ModalDetail.builder()
                .id(modal.getId())
                .type(modal.getType())
                .imageUrl(modal.getImageUrl())
                .leftButtonText(modal.getLeftButtonText())
                .rightButtonText(modal.getRightButtonText())
                .build();
    }

    public List<ModalResponseDTO.ModalDetail> toModalDetailList(List<Modal> modalList) {
        return modalList.stream().map(this::toModalDetail).collect(Collectors.toList());
    }
}
