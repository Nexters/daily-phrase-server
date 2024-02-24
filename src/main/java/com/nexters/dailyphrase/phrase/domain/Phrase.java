package com.nexters.dailyphrase.phrase.domain;

import java.time.LocalDate;

import jakarta.persistence.*;

import com.nexters.dailyphrase.common.domain.BaseDateTimeEntity;
import com.nexters.dailyphrase.phraseimage.domain.PhraseImage;

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

    @Column(length = 10000)
    private String content;

    @Builder.Default private int viewCount = 0;

    @OneToOne(mappedBy = "phrase", cascade = CascadeType.REMOVE)
    private PhraseImage phraseImage;

    @Builder.Default private boolean isReserved = false;

    private LocalDate reservedAt;

    public void setPhraseImage(PhraseImage phraseImage) {
        this.phraseImage = phraseImage;
    }

    // PhraseImage가 다건인 경우 아래와 같이 설정 (주석처리)

    //    @OneToMany(
    //            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
    //            orphanRemoval = true)
    //    @Builder.Default
    //    private List<PhraseImage> phraseImage = new ArrayList<>();
    //
    //    public void setPhraseImage(PhraseImage phraseImage) {
    //        this.phraseImage.add(phraseImage);
    //
    //        // 게시글에 파일이 저장되어있지 않은 경우
    //        if (phraseImage.getPhrase() != this)
    //            // 파일 저장
    //            phraseImage.setPhrase(this);
    //    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
