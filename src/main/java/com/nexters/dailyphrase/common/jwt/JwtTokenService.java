package com.nexters.dailyphrase.common.jwt;

import static com.nexters.dailyphrase.common.consts.DailyPhraseStatic.*;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.nexters.dailyphrase.common.exception.ExpiredTokenException;
import com.nexters.dailyphrase.common.exception.InvalidTokenException;
import com.nexters.dailyphrase.common.exception.RefreshTokenExpiredException;
import com.nexters.dailyphrase.common.jwt.dto.AccessTokenInfo;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class JwtTokenService {

    private final JwtProperties jwtProperties;

    private Jws<Claims> getJws(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token);
        } catch (ExpiredJwtException e) {
            throw ExpiredTokenException.EXCEPTION;
        } catch (Exception e) {
            throw InvalidTokenException.EXCEPTION;
        }
    }

    private Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getSecretKey().getBytes(StandardCharsets.UTF_8));
    }

    private String buildAccessToken(
            Long id, Date issuedAt, Date accessTokenExpiresIn, String role) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, ACCESS_TOKEN)
                .claim(TOKEN_ROLE, role)
                .setExpiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    private String buildRefreshToken(Long id, Date issuedAt, Date accessTokenExpiresIn) {
        final Key encodedKey = getSecretKey();
        return Jwts.builder()
                .setIssuer(TOKEN_ISSUER)
                .setIssuedAt(issuedAt)
                .setSubject(id.toString())
                .claim(TOKEN_TYPE, REFRESH_TOKEN)
                .setExpiration(accessTokenExpiresIn)
                .signWith(encodedKey)
                .compact();
    }

    public String generateAccessToken(Long id, String role) {
        final Date issuedAt = new Date();
        final Date accessTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getAccessExp() * MILLI_TO_SECOND);

        return buildAccessToken(id, issuedAt, accessTokenExpiresIn, role);
    }

    public String generateRefreshToken(Long id) {
        final Date issuedAt = new Date();
        final Date refreshTokenExpiresIn =
                new Date(issuedAt.getTime() + jwtProperties.getRefreshExp() * MILLI_TO_SECOND);
        return buildRefreshToken(id, issuedAt, refreshTokenExpiresIn);
    }

    public boolean isAccessToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(ACCESS_TOKEN);
    }

    public boolean isRefreshToken(String token) {
        return getJws(token).getBody().get(TOKEN_TYPE).equals(REFRESH_TOKEN);
    }

    public AccessTokenInfo parseAccessToken(String token) {
        if (isAccessToken(token)) {
            Claims claims = getJws(token).getBody();
            return AccessTokenInfo.builder()
                    .userId(Long.parseLong(claims.getSubject()))
                    .role((String) claims.get(TOKEN_ROLE))
                    .build();
        }
        throw InvalidTokenException.EXCEPTION;
    }


    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(TOKEN_ROLE) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(TOKEN_ROLE).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSecretKey())
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long parseRefreshToken(String token) {
        try {
            if (isRefreshToken(token)) {
                Claims claims = getJws(token).getBody();
                return Long.parseLong(claims.getSubject());
            }
        } catch (ExpiredTokenException e) {
            throw RefreshTokenExpiredException.EXCEPTION;
        }
        throw InvalidTokenException.EXCEPTION;
    }

    public Long getRefreshTokenTTlSecond() {
        return jwtProperties.getRefreshExp();
    }

    public Long getAccessTokenTTlSecond() {
        return jwtProperties.getAccessExp();
    }
}
