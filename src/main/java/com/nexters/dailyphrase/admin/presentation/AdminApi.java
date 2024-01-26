package com.nexters.dailyphrase.admin.presentation;

import com.nexters.dailyphrase.phrase.presentation.dto.PhraseResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.nexters.dailyphrase.admin.business.AdminFacade;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.common.presentation.CommonResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class AdminApi {
    private final AdminFacade adminFacade;


    @PostMapping("/login")
    public CommonResponse<AdminResponseDTO.LoginAdmin> loginAdmin() {
        return null;
    }

    @PostMapping("/logout")
    public CommonResponse<AdminResponseDTO.LogoutAdmin> logoutAdmin() {
        return null;
    }

    @GetMapping("/phrases")
    public CommonResponse<PhraseResponseDTO.PhraseList> getAdminPhraseList() {
        return null;
    }

    @GetMapping("/phrases/{id}")
    public CommonResponse<PhraseResponseDTO.PhraseDetail> getAdminPhraseDetail(
            @PathVariable final Long id) {
        return null;
    }

    @PostMapping("/phrases")
    @ResponseStatus(HttpStatus.CREATED)
    public CommonResponse<AdminResponseDTO.AddPhrase> addPhrase(@RequestBody final AdminRequestDTO.AddPhrase request) {
        return CommonResponse.onSuccess(adminFacade.addPhrase(request));
    }


    @PatchMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.ModifyPhrase> modifyPhrase(
            @PathVariable Long id, @RequestBody final AdminRequestDTO.ModifyPhrase request) {
        return null;
    }

    @DeleteMapping("/phrases/{id}")
    public CommonResponse<AdminResponseDTO.DeletePhrase> deletePhrase(@PathVariable Long id) {
        return null;
    }
}




