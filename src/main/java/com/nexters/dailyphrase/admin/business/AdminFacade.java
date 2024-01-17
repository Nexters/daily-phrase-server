package com.nexters.dailyphrase.admin.business;

import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.admin.implement.AdminCommandService;
import com.nexters.dailyphrase.admin.implement.AdminQueryService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminFacade {
    private final AdminQueryService adminQueryService;
    private final AdminCommandService adminCommandService;
}
