package com.nexters.dailyphrase.common.presentation.converter;

import org.springframework.core.convert.converter.Converter;

import com.nexters.dailyphrase.common.enums.SocialType;

public class SocialTypeRequestConverter implements Converter<String, SocialType> {
    @Override
    public SocialType convert(String source) {
        return SocialType.findByValue(source);
    }
}
