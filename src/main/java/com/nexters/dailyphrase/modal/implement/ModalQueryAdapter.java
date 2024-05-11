package com.nexters.dailyphrase.modal.implement;

import com.nexters.dailyphrase.common.annotation.Adapter;
import com.nexters.dailyphrase.modal.domain.repository.ModalRepository;

import lombok.RequiredArgsConstructor;

@Adapter
@RequiredArgsConstructor
public class ModalQueryAdapter {
    private final ModalRepository modalRepository;
}
