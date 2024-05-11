package com.nexters.dailyphrase.modal.presentation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "08-Modal 🏞️", description = "모달 관련 API")
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/modals")
public class ModalApi {

}
