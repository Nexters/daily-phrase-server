package com.nexters.dailyphrase.admin.presentation;

import com.nexters.dailyphrase.admin.business.AdminFacade;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.common.presentation.CommonResponse;
import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApi {
    private final AdminFacade adminFacade;

    @PostMapping("/phrases")
    public CommonResponse<AdminResponseDTO.AddPhrase> addPhrase(@RequestBody final AdminRequestDTO.AddPhrase request) {
        return null;
    }

    @PatchMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.ModifyPhrase> modifyPhrase(@PathVariable Long id, @RequestBody final AdminRequestDTO.ModifyPhrase request) {
        return null;
    }

    @DeleteMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.RemovePhrase> removePhrase(@PathVariable Long id) {

    }
}
