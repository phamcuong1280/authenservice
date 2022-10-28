package com.example.accountservice.usecases.token;

import com.example.accountservice.infrastructure.redismodel.RefreshToken;

public interface ITokenUseCase {
    boolean saveToken(RefreshToken refreshToken);

    String getToken(String uuid);
}
