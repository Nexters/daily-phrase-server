package com.nexters.dailyphrase.phraseimage.domain;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.phrase.domain.Phrase;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class PhraseImage extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Phrase phrase;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false, length = 1000)
    private String url; // filePath

    private Long fileSize;

    private String imageRatio;

    public PhraseImage(String fileName, String url, Long fileSize) {
        this.fileName = fileName;
        this.url = url;
        this.fileSize = fileSize;
    }

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
