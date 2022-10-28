package com.example.accountservice.infrastructure.redismodel;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("refresh_token")
public class RefreshToken {
    private String uuid;
    private String refreshToken;
}
