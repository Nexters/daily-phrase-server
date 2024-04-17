package com.nexters.dailyphrase.admin.business;

import java.time.LocalDateTime;
import java.util.Optional;

import com.nexters.dailyphrase.common.annotation.Mapper;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.admin.domain.Admin;
import com.nexters.dailyphrase.admin.presentation.dto.AdminRequestDTO;
import com.nexters.dailyphrase.admin.presentation.dto.AdminResponseDTO;
import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

@Mapper
public class AdminMapper {

    public Phrase toPhrase(AdminRequestDTO.AddPhrase request) {
        return Phrase.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .isReserved(request.getIsReserved())
                .publishDate(request.getPublishDate())
                .build();
    }

    // 이미지 다건일때
    //    public List<PhraseImage> toPhraseImage(AdminRequestDTO.AddPhrase request) {
    //        List<PhraseImage> phraseImages = new ArrayList<>();
    //
    //        for (AdminRequestDTO.ImageListItem imageItem : request.getImages()) {
    //            String url = imageItem.getImageUrl();
    //            String imageRatio = imageItem.getImageRatio();
    //            String fileName = imageItem.getFileName();
    //            Long fileSize = imageItem.getFileSize();
    //
    //            PhraseImage phraseImage =
    //                    PhraseImage.builder()
    //                            .url(url)
    //                            .imageRatio(imageRatio)
    //                            .fileName(fileName)
    //                            .fileSize(fileSize)
    //                            .build();
    //
    //            phraseImages.add(phraseImage);
    //        }
    //
    //        return phraseImages;
    //    }

    public PhraseImage toPhraseImage(AdminRequestDTO.AddPhrase request) {
        return PhraseImage.builder()
                .url(request.getImageUrl())
                .imageRatio(request.getImageRatio())
                .fileName(request.getFileName())
                .fileSize(request.getFileSize())
                .build();
    }

    public AdminResponseDTO.AddPhrase toAddPhrase(Phrase savedPhrase) {
        return AdminResponseDTO.AddPhrase.builder()
                .id(savedPhrase.getId())
                .createdAt(savedPhrase.getCreatedAt())
                .build();
    }

    // 이미지 다건일때
    //    public AdminResponseDTO.UploadImageFiles toUploadImageFiles(
    //            List<AdminResponseDTO.ImageListItem> imageList) {
    //        return AdminResponseDTO.UploadImageFiles.builder().images(imageList).build();
    //    }

    public AdminResponseDTO.UploadImageFile toUploadImageFile(
            String url, Long fileSize, String fileName) {
        return AdminResponseDTO.UploadImageFile.builder()
                .imageUrl(url)
                .fileName(fileName)
                .fileSize(fileSize)
                .build();
    }

    public AdminResponseDTO.AdminPhraseDetail toAdminPhraseDetail(Phrase phrase) {
        String imageUrl =
                Optional.ofNullable(phrase.getPhraseImage()).map(PhraseImage::getUrl).orElse("");

        return AdminResponseDTO.AdminPhraseDetail.builder()
                .title(phrase.getTitle())
                .imageUrl(imageUrl)
                .content(phrase.getContent())
                .build();
    }

    public Phrase toPhrase(AdminRequestDTO.ModifyPhrase request) {
        return Phrase.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .isReserved(request.getIsReserved())
                .publishDate(request.getPublishDate())
                .build();
    }

    public PhraseImage toPhraseImage(AdminRequestDTO.ModifyPhrase request) {
        return PhraseImage.builder()
                .fileName(request.getFileName())
                .imageRatio(request.getImageRatio())
                .fileSize(request.getFileSize())
                .url(request.getImageUrl())
                .build();
    }

    public AdminResponseDTO.ModifyPhrase toModifyPhrase(Phrase updatedPhrase) {
        return AdminResponseDTO.ModifyPhrase.builder()
                .id(updatedPhrase.getId())
                .updatedAt(updatedPhrase.getUpdatedAt())
                .createdAt(updatedPhrase.getCreatedAt())
                .title(updatedPhrase.getTitle())
                .imageUrl(updatedPhrase.getPhraseImage().getUrl())
                .content(updatedPhrase.getContent())
                .build();
    }

    public AdminResponseDTO.DeletePhrase toDeletePhrase(Long id) {
        return AdminResponseDTO.DeletePhrase.builder()
                .phraseId(id)
                .deletedAt(LocalDateTime.now())
                .build();
    }

    public AdminResponseDTO.LoginAdmin toLogin(
            Admin admin, String accessToken, String refreshToken) {
        return AdminResponseDTO.LoginAdmin.builder()
                .userId(admin.getUserId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
