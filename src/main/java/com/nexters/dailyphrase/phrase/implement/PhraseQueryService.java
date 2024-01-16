package com.nexters.dailyphrase.phrase.implement;

import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PhraseQueryService {
    private final PhraseRepository phraseRepository;
}
