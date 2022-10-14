package com.example.accountservice.usecase.account;

import com.example.accountservice.common.GooglePojo;
import com.example.accountservice.exception.UserNotFound;
import com.example.accountservice.models.*;
import com.example.accountservice.payload.request.LoginRequest;
import com.example.accountservice.payload.request.SignupRequest;
import com.example.accountservice.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
public class AccountUseCase implements IAccountUseCase {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final ProfileRepository profileRepository;
    private final AccountRoleRepository accountRoleRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder encoder;

    public static Account getRole(LoginRequest request, RoleRepository roleRepository, AccountRepository accountRepository, ProfileRepository profileRepository, AccountRoleRepository accountRoleRepository, PasswordEncoder encoder) {
        Role role = roleRepository.findByRole(ERole.ROLE_USER).orElse(null);
        if (role == null) {
            role = roleRepository.save(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
        }

        var account = accountRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFound::new);
        var profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setAccountUuid(account.getUuid());
        profile.setEmail(request.getEmail());
        profileRepository.save(profile);
        var accountRole = new AccountRole(account.getUuid(), role.getUuid());
        accountRoleRepository.save(accountRole);
        account.setUuid(UUID.randomUUID().toString());
        account.setPassword(encoder.encode(request.getPassword()));
        return accountRepository.save(account);
    }

    @Override
    public Account loginWithGoogle(GooglePojo googlePojo) {
        Account account = new Account();
        account.setUuid(UUID.randomUUID().toString());
        account.setEmail(googlePojo.getEmail());
        account.setUsername(googlePojo.getName());
        account = accountRepository.save(account);
        Profile profile = new Profile();
        profile.setAccountUuid(account.getUuid());
        profile.setUuid(UUID.randomUUID().toString());
        profile.setFirstName(googlePojo.getFamily_name());
        profile.setLastName(googlePojo.getGiven_name());
        profile.setFullName(googlePojo.getName());
        profile.setEmail(googlePojo.getEmail());
        profile.setImage(googlePojo.getPicture());
        profileRepository.save(profile);

        Role role = roleRepository.findByRole(ERole.ROLE_USER).orElse(null);
        if (role == null) {
            role = roleRepository.save(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
        }
        AccountRole accountRole = new AccountRole(account.getUuid(), role.getUuid());
        accountRoleRepository.save(accountRole);
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
            role = roleRepository.save(new Role(UUID.randomUUID().toString(), request.getRole()));
        } else {
            role = roleRepository.findByRole(ERole.ROLE_USER).orElse(null);
            if (role == null) {
                role = roleRepository.save(new Role(UUID.randomUUID().toString(), ERole.ROLE_USER));
            }
        }
        account.setUuid(UUID.randomUUID().toString());
        account = accountRepository.save(account);
        var profile = new Profile();
        profile.setUuid(UUID.randomUUID().toString());
        profile.setEmail(request.getEmail());
        profile.setAccountUuid(account.getUuid());
        profileRepository.save(profile);


        var accountRole = new AccountRole(account.getUuid(), role.getUuid());
        accountRoleRepository.save(accountRole);
        return account;
    }

    @Override
    public Boolean existsByEmail(String email) {
        return accountRepository.existsByEmail(email);
    }
}
