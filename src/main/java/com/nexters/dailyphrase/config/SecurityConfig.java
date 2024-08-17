package com.nexters.dailyphrase.config;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexters.dailyphrase.admin.exception.AdminErrorCode;
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
        "/",
        "/swagger-ui/**",
        "/api/v1/phrases/**",
        "/api/v1/events/info",
        "/api/v1/events/prizes",
        "/api/v1/events/kakaolink/callback",
        "/api/v1/members/login/{socialType}",
        "/api/v1/modals",
        "/api/admin/login",
        "/api-docs/**"
    };

    @Component
    public static class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

        @Override
        public void commence(
                HttpServletRequest request,
                HttpServletResponse response,
                AuthenticationException authException)
                throws IOException {
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

            AdminErrorCode unauthorizedError = AdminErrorCode.ADMIN_UNAUTHORIZED_EXCEPTION;

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode ErrorNode = objectMapper.createObjectNode();
            ErrorNode.put("isSuccess", "false");
            ErrorNode.put("code", unauthorizedError.getCode());
            ErrorNode.put("message", unauthorizedError.getReason());
            ErrorNode.put("status", HttpServletResponse.SC_UNAUTHORIZED);
            String ErrorResponse = objectMapper.writeValueAsString(ErrorNode);

            response.getWriter().write(ErrorResponse);
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
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);

            AdminErrorCode forbiddenError = AdminErrorCode.ADMIN_FORBIDDEN_EXCEPTION;

            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode ErrorNode = objectMapper.createObjectNode();
            ErrorNode.put("isSuccess", "false");
            ErrorNode.put("code", forbiddenError.getCode());
            ErrorNode.put("message", forbiddenError.getReason());
            ErrorNode.put("status", HttpServletResponse.SC_FORBIDDEN);
            String ErrorResponse = objectMapper.writeValueAsString(ErrorNode);

            response.getWriter().write(ErrorResponse);
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
                                        .requestMatchers(
                                                "/api/v1/members/**",
                                                "/api/v1/likes/**",
                                                "/api/v1/favorites/**")
                                        .authenticated()
                                        .requestMatchers(HttpMethod.OPTIONS, "/**")
                                        .permitAll()
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
