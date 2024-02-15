package com.nexters.dailyphrase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.nexters.dailyphrase.common.presentation.converter.SocialTypeRequestConverter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class GlobalWebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverter(new SocialTypeRequestConverter());
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http://localhost:3020",
                        "http://localhost:8080",
                        "https://daily-phrase-web-web-kappa.vercel.app",
                        "http://www.daily-phrase.com",
                        "http://admin.daily-phrase.com")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(6000);
    }

    public void addPhraseCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/phrase/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(6000);
    }
}
