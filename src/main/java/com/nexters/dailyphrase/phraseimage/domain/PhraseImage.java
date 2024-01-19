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

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private Phrase phrase;

    private String fileName;

    private String uuid;

    private String url;

    public void setPhrase(Phrase phrase) {
        if (this.phrase != null) {
            this.phrase.setPhraseImage(null);
        }

        this.phrase = phrase;

        if (phrase != null) {
            phrase.setPhraseImage(this);
        }
    }
}
