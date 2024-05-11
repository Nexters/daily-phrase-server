package com.nexters.dailyphrase.modal.implement;

import java.util.List;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.common.enums.ModalType;
import com.nexters.dailyphrase.modal.domain.Modal;
import com.nexters.dailyphrase.modal.domain.repository.ModalRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ModalQueryAdapter {
    private final ModalRepository modalRepository;

    public List<Modal> findAllByType(final ModalType type) {
        return type == null ? modalRepository.findAll() : modalRepository.findAllByType(type);
    }
}
