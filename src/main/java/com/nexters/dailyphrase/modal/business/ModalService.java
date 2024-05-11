package com.nexters.dailyphrase.modal.business;


import com.nexters.dailyphrase.modal.implement.ModalQueryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModalService {
    private final ModalQueryAdapter modalQueryAdapter;
}
