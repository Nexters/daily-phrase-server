package com.nexters.dailyphrase.admin.presentation.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AdminRequestDTO {

    @Getter
    public static class AddPhrase {
        private String field;
    }

    @Getter
    public static class ModifyPhrase {
        private String field;
    }
}
