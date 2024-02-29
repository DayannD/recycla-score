package com.simplon.recyclascore.services.IServices;

import com.simplon.recyclascore.exception.JwtExpiredException;
import com.simplon.recyclascore.models.RefreshToken;

import java.util.Optional;

public interface IRefreshTokenService {
    RefreshToken createRefreshToken(String username, String token);

    Optional<RefreshToken> findByToken(String token);

    RefreshToken verifyExpiration(RefreshToken refreshToken) throws JwtExpiredException;
}
