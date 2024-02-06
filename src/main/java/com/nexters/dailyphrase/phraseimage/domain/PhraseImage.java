package com.nexters.dailyphrase.phraseimage.domain;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.phrase.domain.Phrase;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PhraseImage extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = true)
    // 이미지 다건이면 @ManyToOne(fetch=FetchType.LAZY)로 설정
    private Phrase phrase;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, length = 1000)
    private String url; // filePath

    private Long fileSize;

    private String imageRatio;

    public void setPhrase(Phrase phrase) {
        if (this.phrase != null) {
            this.phrase.setPhraseImage(null);
        }

        this.phrase = phrase;

        if (phrase != null) {
            phrase.setPhraseImage(this);
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setImageRatio(String imageRatio) {
        this.imageRatio = imageRatio;
    }
}
