package com.nexters.dailyphrase.admin.business;

import java.io.InputStream;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
    private final AmazonS3Client amazonS3Client;

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

    // 이미지 다건일때(주석처리)
    //    @Transactional
    //    public AdminResponseDTO.UploadImageFiles uploadImageFiles(final List<MultipartFile>
    // images)
    //            throws Exception {
    //
    //        final List<AdminResponseDTO.ImageListItem> imageList =
    // fileHandler.parseFileInfo(images);
    //
    //        return adminMapper.toUploadImageFiles(imageList);
    //    }
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Transactional
    public AdminResponseDTO.UploadImageFile uploadImageFile(final MultipartFile image)
            throws Exception {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(image.getContentType());
        objectMetadata.setContentLength(image.getSize());

        String originalFilename = image.getOriginalFilename();
        int index = originalFilename.lastIndexOf(".");
        String ext = originalFilename.substring(index + 1);

        String storeFileName = UUID.randomUUID() + "." + ext;
        String key = "images/" + storeFileName; // 폴더명+파일이름

        try (InputStream inputStream = image.getInputStream()) {
            amazonS3Client.putObject(
                    new PutObjectRequest(bucket, key, inputStream, objectMetadata)
                            .withCannedAcl(CannedAccessControlList.PublicRead));
        }

        String storeFileUrl = amazonS3Client.getUrl(bucket, key).toString();

        return adminMapper.toUploadImageFile(storeFileUrl, image.getSize(), originalFilename);
    }

    // 이미지 다건 일때
    //    @Transactional
    //    public AdminResponseDTO.AddPhrase addPhrase(final AdminRequestDTO.AddPhrase request) {
    //
    //        final Phrase phrase = adminMapper.toPhrase(request);
    //        final List<PhraseImage> phraseImages = adminMapper.toPhraseImage(request);
    //
    //        Phrase savedPhrase = phraseCommandService.create(phrase);
    //
    //        for (PhraseImage phraseImage : phraseImages) {
    //            phraseImage.setPhrase(savedPhrase);
    //            phraseImageCommandService.create(phraseImage);
    //        }
    //
    //        return adminMapper.toAddPhrase(savedPhrase);
    //    }

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
