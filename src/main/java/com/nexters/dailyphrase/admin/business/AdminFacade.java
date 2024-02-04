package com.nexters.dailyphrase.admin.business;

import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.nexters.dailyphrase.admin.domain.Admin;
import com.nexters.dailyphrase.admin.implement.AdminQueryService;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.common.jwt.JwtTokenService;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.implement.PhraseCommandService;
import com.nexters.dailyphrase.phrase.implement.PhraseQueryService;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import com.nexters.dailyphrase.phraseimage.implement.PhraseImageCommandService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final PhraseCommandService phraseCommandService;
    private final PhraseQueryService phraseQueryService;
    private final AdminQueryService adminQueryService;
    private final PhraseImageCommandService phraseImageCommandService;
    private final AdminMapper adminMapper;
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @Transactional
    public AdminResponseDTO.LoginAdmin loginAdmin(final AdminRequestDTO.LoginAdmin request) {

        String username = request.getUserId();
        String password = request.getPassword();

        adminQueryService.findByLoginId(username); // UserId Notfound 예외처리용

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        Authentication authentication =
                authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        String role =
                authentication.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")); // role_admin
        String authenticatedUserId = authentication.getName(); //  UserId

        Admin admin = adminQueryService.findByLoginId(authenticatedUserId); // id
        String accessToken = jwtTokenService.generateAccessToken(admin.getId(), role);
        String refreshToken = jwtTokenService.generateRefreshToken(admin.getId());

        return adminMapper.toLogin(admin, accessToken, refreshToken);
    }

    @Transactional
    public AdminResponseDTO.AddPhrase addPhrase(final AdminRequestDTO.AddPhrase request) {

        final Phrase phrase = adminMapper.toPhrase(request);
        final PhraseImage phraseImage = adminMapper.toPhraseImage(request);

        Phrase savedPhrase = phraseCommandService.create(phrase);
        phraseImage.setPhrase(savedPhrase);
        phraseImageCommandService.create(phraseImage);

        return adminMapper.toAddPhrase(savedPhrase);
    }

    @Transactional(readOnly = true)
    public AdminResponseDTO.AdminPhraseDetail getAdminPhraseDetail(final Long id) {
        Phrase phrase = phraseQueryService.findById(id);
        return adminMapper.toAdminPhraseDetail(phrase);
    }

    @Transactional
    public AdminResponseDTO.ModifyPhrase modifyPhrase(
            final Long id, final AdminRequestDTO.ModifyPhrase request) {

        final Phrase requestedPhrase = adminMapper.toPhrase(request);
        final PhraseImage requestedPhraseImage = adminMapper.toPhraseImage(request);

        Phrase updatedPhrase = phraseQueryService.findById(id);
        updatedPhrase.setTitle(requestedPhrase.getTitle());
        updatedPhrase.setContent(requestedPhrase.getContent());

        PhraseImage updatedPhraseImage = updatedPhrase.getPhraseImage();
        updatedPhraseImage.setImageRatio(requestedPhraseImage.getImageRatio());
        updatedPhraseImage.setFileName(requestedPhraseImage.getFileName());

        return adminMapper.toModifyPhrase(updatedPhrase);
    }

    @Transactional(readOnly = true)
    public AdminResponseDTO.AdminPhraseList getAdminPhraseList() {
        return phraseQueryService.findAdminPhraseListDTO();
    }

    @Transactional
    public AdminResponseDTO.DeletePhrase deletePhrase(final Long id) {

        phraseImageCommandService.deleteByPhraseId(id);
        phraseCommandService.deleteById(id);

        return adminMapper.toDeletePhrase();
    }
}
