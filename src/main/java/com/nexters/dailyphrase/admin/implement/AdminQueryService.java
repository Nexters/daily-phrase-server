package com.nexters.dailyphrase.admin.implement;

import com.nexters.dailyphrase.phrase.domain.Phrase;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;
import com.nexters.dailyphrase.phrase.exception.PhraseNotFoundException;
import org.springframework.stereotype.Service;

import com.nexters.dailyphrase.admin.domain.repository.AdminRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminQueryService {
    private final AdminRepository adminRepository;
    private final PhraseRepository phraseRepository;


}
