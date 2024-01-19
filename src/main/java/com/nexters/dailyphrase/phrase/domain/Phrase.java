package com.nexters.dailyphrase.phrase.domain;

import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;
import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;

import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Phrase extends BaseDateTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @Builder.Default
    private int viewCount = 0;

    @OneToOne(mappedBy = "phrase", cascade = CascadeType.REMOVE)
    private PhraseImage phraseImage;

    public void setPhraseImage(PhraseImage phraseImage) {
        this.phraseImage = phraseImage;
    }
}
