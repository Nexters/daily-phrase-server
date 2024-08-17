package com.nexters.dailyphrase.member.implement.strategy;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.member.domain.Member;

public interface SocialLoginStrategy {
    Member login(String identityToken);

    SocialType getSocialType();
}
