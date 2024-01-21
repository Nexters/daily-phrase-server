package com.nexters.dailyphrase.member.implement.factory;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.common.enums.SocialType;
import com.nexters.dailyphrase.member.implement.SocialLoginService;
import com.nexters.dailyphrase.member.implement.strategy.SocialLoginStrategy;

@Component
public class SocialLoginServiceFactory {
    private final Map<SocialType, SocialLoginService> contextMap;

    @Autowired
    public SocialLoginServiceFactory(List<SocialLoginStrategy> strategies) {
        contextMap = new EnumMap<>(SocialType.class);
        for (SocialLoginStrategy strategy : strategies) {
            SocialType socialType = strategy.getSocialType();
            contextMap.put(socialType, new SocialLoginService(strategy));
        }
    }

    public SocialLoginService getServiceBySocialType(SocialType socialType) {
        return contextMap.get(socialType);
    }
}
