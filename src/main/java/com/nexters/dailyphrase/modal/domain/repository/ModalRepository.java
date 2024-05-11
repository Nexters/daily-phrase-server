package com.nexters.dailyphrase.modal.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nexters.dailyphrase.common.enums.ModalType;
import com.nexters.dailyphrase.modal.domain.Modal;

public interface ModalRepository extends JpaRepository<Modal, Long> {
    List<Modal> findAllByType(ModalType type);
}
