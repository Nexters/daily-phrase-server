package com.nexters.dailyphrase.admin.presentation;

import com.nexters.dailyphrase.admin.business.AdminFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminApi {
    private final AdminFacade adminFacade;
}
