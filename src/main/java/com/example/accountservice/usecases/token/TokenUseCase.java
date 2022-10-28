package com.example.accountservice.usecases.token;


import com.example.accountservice.infrastructure.redismodel.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenUseCase implements ITokenUseCase {

    @Autowired
    private RedisTemplate<String, String> template;
    private String key = "USER";

    @Override
    public boolean saveToken(RefreshToken refreshToken) {
        template.opsForHash().put(key, refreshToken.getUuid(), refreshToken.getRefreshToken());

        return false;
    }

    @Override
    public String getToken(String uuid) {
        Object o = template.opsForHash().get(key, uuid);
        assert o != null;
        return o.toString();
    }
}
