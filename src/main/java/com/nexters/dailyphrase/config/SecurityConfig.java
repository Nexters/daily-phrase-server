package com.nexters.dailyphrase.config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.common.jwt.JwtAuthorizationFilter;
import com.nexters.dailyphrase.common.jwt.JwtTokenService;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtTokenService jwtTokenService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    private final String[] allowedUrls = {
        "/", "/swagger-ui/**", "/api/v1/**", "/api/admin/login"
    };

    @Component
    public static class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(
                HttpServletRequest request,
                HttpServletResponse response,
                AuthenticationException authException)
                throws IOException {

            // 유효한 자격증명을 제공하지 않고 접근하려 할때 401 InvalidTokenException.EXCEPTION
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Component
    public static class JwtAccessDeniedHandler implements AccessDeniedHandler {
        @Override
        public void handle(
                HttpServletRequest request,
                HttpServletResponse response,
                AccessDeniedException accessDeniedException)
                throws IOException, ServletException {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
        }
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(
                        sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(
                        (exception) ->
                                exception
                                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                                        .accessDeniedHandler(jwtAccessDeniedHandler))
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers(allowedUrls)
                                        .permitAll()
                                        .requestMatchers("/api/admin/**")
                                        .hasAuthority("ROLE_ADMIN")
                                        .anyRequest()
                                        .authenticated())
                .addFilterBefore(
                        new JwtAuthorizationFilter(jwtTokenService),
                        UsernamePasswordAuthenticationFilter.class)
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt Encoder 사용
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
