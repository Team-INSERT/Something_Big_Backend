package com.insert.ogbsm.service.auth;

import com.insert.ogbsm.domain.auth.RefreshToken;
import com.insert.ogbsm.domain.auth.repo.RefreshTokenRepo;
import com.insert.ogbsm.global.jwt.dto.TokenResponseDto;
import com.insert.ogbsm.global.jwt.exception.RefreshTokenExpiredException;
import com.insert.ogbsm.global.jwt.util.JwtProvider;
import com.insert.ogbsm.global.jwt.util.JwtUtil;
import com.insert.ogbsm.presentation.auth.dto.UsingRefreshTokenReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccessTokenRefreshService {
    private final RefreshTokenRepo refreshTokenRepo;
    private final JwtProvider jwtProvider;
    private final JwtUtil jwtUtil;

    public TokenResponseDto execute(final UsingRefreshTokenReq usingRefreshTokenReq) {
        RefreshToken redisRefreshToken = refreshTokenRepo.findByRefreshToken(
                jwtUtil.replaceBearer(usingRefreshTokenReq.getRefreshToken())
        ).orElseThrow(() -> RefreshTokenExpiredException.EXCEPTION);
        return getNewAccessTokens(redisRefreshToken);
    }

    private TokenResponseDto getNewAccessTokens(final RefreshToken redisRefreshToken) {
        String newAccessToken = jwtProvider.generateAccessToken(redisRefreshToken.getId(), redisRefreshToken.getRole());

        return TokenResponseDto.builder()
                .accessToken(newAccessToken)
                .refreshToken(redisRefreshToken.getRefreshToken())
                .expiredAt(redisRefreshToken.getExpiredAt())
                .build();
    }
}
