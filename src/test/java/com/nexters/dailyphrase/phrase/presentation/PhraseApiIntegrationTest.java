package com.nexters.dailyphrase.phrase.presentation;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.nexters.dailyphrase.like.domain.repository.LikeRepository;
import com.nexters.dailyphrase.member.domain.repository.MemberRepository;
import com.nexters.dailyphrase.phrase.domain.repository.PhraseRepository;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PhraseApiIntegrationTest {
    @Autowired private WebApplicationContext webApplicationContext;
    @Autowired private MemberRepository memberRepository;
    @Autowired private PhraseRepository phraseRepository;
    @Autowired private LikeRepository likeRepository;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
}
