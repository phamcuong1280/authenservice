package com.example.accountservice.usecases.account;

import com.example.accountservice.common.exception.UserNotFound;
import com.example.accountservice.common.googleDto.GooglePojo;
import com.example.accountservice.common.web.Resource;
import com.example.accountservice.common.web.ServiceClient;
import com.example.accountservice.controllers.payload.request.LoginRequest;
import com.example.accountservice.controllers.payload.request.SignupRequest;
import com.example.accountservice.infrastructure.models.*;
import com.example.accountservice.infrastructure.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
public class AccountUseCase extends ServiceClient implements IAccountUseCase{
    private final JpaAccountRepository jpaAccountRepository;
    private final JpaRoleRepository jpaRoleRepository;
    private final JpaProfileRepository jpaProfileRepository;
    private final JpaAccountRoleRepository jpaAccountRoleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder encoder;
    private final String url;
    private final String basicAuth;

    @Autowired
    public AccountUseCase(JpaAccountRepository jpaAccountRepository, JpaRoleRepository jpaRoleRepository,
                          JpaProfileRepository jpaProfileRepository, JpaAccountRoleRepository jpaAccountRoleRepository,
                          RefreshTokenRepository refreshTokenRepository, PasswordEncoder encoder,
                          @Value("${spring.service.product-service.cart.url}")
                          String url,
                          @Value("${spring.service.product-service.basic-auth}")
                          String basicAuth) {
        this.jpaAccountRepository = jpaAccountRepository;
        this.jpaRoleRepository = jpaRoleRepository;
        this.jpaProfileRepository = jpaProfileRepository;
        this.jpaAccountRoleRepository = jpaAccountRoleRepository;
        this.refreshTokenRepository = refreshTokenRepository;
        this.encoder = encoder;
        this.url = url;
        this.basicAuth = basicAuth;
    }

    public static Account getRole(LoginRequest request, JpaRoleRepository jpaRoleRepository, JpaAccountRepository jpaAccountRepository, JpaProfileRepository jpaProfileRepository, JpaAccountRoleRepository jpaAccountRoleRepository, PasswordEncoder encoder) {
        Role role = jpaRoleRepository.findByRole(ERole.ROLE_USER).orElse(null);
        if (role == null) {
            role = jpaRoleRepository.save(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
        }

        var account = jpaAccountRepository.findByEmailAndStatusIsTrue(request.getEmail()).orElseThrow(UserNotFound::new);
        var profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setAccountUuid(account.getUuid());
        profile.setEmail(request.getEmail());
        jpaProfileRepository.save(profile);


        var accountRole = new AccountRole(account.getUuid(), role.getUuid());
        jpaAccountRoleRepository.save(accountRole);
        account.setUuid(UUID.randomUUID().toString());
        account.setPassword(encoder.encode(request.getPassword()));
        return jpaAccountRepository.save(account);
    }

    @Override
    public Account loginWithGoogle(GooglePojo googlePojo) {
        Account account = new Account();
        account.setUuid(UUID.randomUUID().toString());
        account.setEmail(googlePojo.getEmail());
        account.setUsername(googlePojo.getName());
        account = jpaAccountRepository.save(account);
        Profile profile = new Profile();
        profile.setAccountUuid(account.getUuid());
        profile.setUuid(UUID.randomUUID().toString());
        profile.setFirstName(googlePojo.getFamily_name());
        profile.setLastName(googlePojo.getGiven_name());
        profile.setFullName(googlePojo.getName());
        profile.setEmail(googlePojo.getEmail());
        profile.setImage(googlePojo.getPicture());
        jpaProfileRepository.save(profile);

        Role role = jpaRoleRepository.findByRole(ERole.ROLE_USER).orElse(null);
        if (role == null) {
            role = jpaRoleRepository.save(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
        }
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
        var account = new Account(request.getUserName(), request.getEmail(), encoder.encode(request.getPassword()));
        Role role;
        if (request.getRole() != null) {
            role = jpaRoleRepository.save(new Role(UUID.randomUUID().toString(), request.getRole()));
        } else {
            role = jpaRoleRepository.findByRole(ERole.ROLE_USER).orElse(null);
            if (role == null) {
                role = jpaRoleRepository.save(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
            }
        }
        account.setUuid(UUID.randomUUID().toString());
        account = jpaAccountRepository.save(account);

        get(url+"/"+ account.getUuid(), Resource.class, basicAuth);
        var profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setEmail(request.getEmail());
        profile.setAccountUuid(account.getUuid());
        jpaProfileRepository.save(profile);


        var accountRole = new AccountRole(account.getUuid(), role.getUuid());
        jpaAccountRoleRepository.save(accountRole);
        return account;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return jpaAccountRepository.existsByEmail(email);
    }
}
