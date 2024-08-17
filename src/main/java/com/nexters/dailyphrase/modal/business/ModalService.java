package com.nexters.dailyphrase.modal.business;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.common.enums.ModalType;
import com.nexters.dailyphrase.modal.domain.Modal;
import com.nexters.dailyphrase.modal.implement.ModalQueryAdapter;
import com.nexters.dailyphrase.modal.presentation.dto.ModalResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModalService {
    private final ModalQueryAdapter modalQueryAdapter;
    private final ModalMapper modalMapper;

    @Transactional(readOnly = true)
    public List<ModalResponseDTO.ModalDetail> getModalList(final ModalType type) {
        List<Modal> modalList = modalQueryAdapter.findAllByType(type);
        return modalMapper.toModalDetailList(modalList);
    }
}
