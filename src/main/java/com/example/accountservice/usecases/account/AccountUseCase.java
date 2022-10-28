package com.example.accountservice.usecases.account;

import com.example.accountservice.common.exception.AuthenticationException;
import com.example.accountservice.common.exception.UserNotFound;
import com.example.accountservice.common.googleDto.GooglePojo;
import com.example.accountservice.common.web.ServiceClient;
import com.example.accountservice.controllers.payload.request.LoginRequest;
import com.example.accountservice.controllers.payload.request.SignupRequest;
import com.example.accountservice.infrastructure.models.*;
import com.example.accountservice.infrastructure.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountUseCase extends ServiceClient implements IAccountUseCase {
    private final JpaAccountRepository jpaAccountRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaProfileRepository jpaProfileRepository;
    private final JpaAccountRoleRepository jpaAccountRoleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder encoder;
    private final AccountMapper accountMapper;

    @Override
    public Account loginWithGoogle(GooglePojo googlePojo) {
        var current = jpaAccountRepository.findByEmail(googlePojo.getEmail());
        if (current.isPresent()){
            throw new AuthenticationException();
        }

        var account = jpaAccountRepository.save(accountMapper.from(googlePojo));
        jpaProfileRepository.save(accountMapper.from(account, googlePojo));

        Role role = jpaRoleRepository.findByRole(ERole.ROLE_USER).orElse(null);
        assert role != null;
        AccountRole accountRole = new AccountRole(account.getUuid(), role.getUuid());
        jpaAccountRoleRepository.save(accountRole);
        return account;
    }

    @Override
    public RefreshToken createRefreshToken(String accountUuid) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setAccountUuid(accountUuid);
        refreshToken.setRefreshToken(UUID.randomUUID().toString());
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public Account createUser(SignupRequest request) {
        var account = accountMapper.from(request);
        account.setPassword(encoder.encode(request.getPassword()));
        Role role = jpaRoleRepository.findByRole(ERole.ROLE_USER).orElse(null);
        account = jpaAccountRepository.save(account);

//        get(url+"/"+ account.getUuid(), Resource.class, basicAuth);
        var profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setEmail(request.getEmail());
        profile.setAccountUuid(account.getUuid());
        jpaProfileRepository.save(profile);

        assert role != null;
        var accountRole = new AccountRole(account.getUuid(), role.getUuid());
        jpaAccountRoleRepository.save(accountRole);
        return account;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaAccountRepository.existsByEmail(email);
    }
}
