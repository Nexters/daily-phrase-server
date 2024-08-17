package com.nexters.dailyphrase.modal.presentation;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nexters.dailyphrase.common.enums.ModalType;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.modal.business.ModalService;
import com.nexters.dailyphrase.modal.presentation.dto.ModalResponseDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "08-Modal 🏞️", description = "모달 관련 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/modals")
public class ModalApi {
    private final ModalService modalService;

    @GetMapping
    @Operation(summary = "08-01 Modal 🏞 모달 정보 조회 Made By 성훈", description = "모달 정보 조회 API입니다.")
    public CommonResponse<List<ModalResponseDTO.ModalDetail>> getModalList(
            @RequestParam(required = false) final ModalType type) {
        return CommonResponse.onSuccess(modalService.getModalList(type));
    }
}
