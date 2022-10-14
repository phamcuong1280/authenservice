package com.example.accountservice.controllers;

import com.example.accountservice.config.rest.BaseResponse;
import com.example.accountservice.exception.HousingErrors;
import com.example.accountservice.exception.UserNotFound;
import com.example.accountservice.exception.constant.HousingException;
import com.example.accountservice.models.RefreshToken;
import com.example.accountservice.payload.request.LoginRequest;
import com.example.accountservice.payload.request.SignupRequest;
import com.example.accountservice.payload.response.JwtResponse;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.security.jwt.JwtUtils;
import com.example.accountservice.security.services.UserDetailsServiceImpl;
import com.example.accountservice.security.services.UserPrincipal;
import com.example.accountservice.usecase.account.IAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;
    private final AccountRepository accountRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final IAccountUseCase accountUseCase;

    @PostMapping("/signin")
    public BaseResponse<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(request.getEmail());
        boolean matches = encoder.matches(request.getPassword(), userDetails.getPassword());
        if (!matches) {
            return BaseResponse.ofFailed(HousingErrors.USER_NOT_UNAUTHORIZED);
        }
        return getBaseResponse(userDetails);
    }

    @PostMapping("/signup")
    public BaseResponse<?> registerUser(@Valid @RequestBody SignupRequest request) {
        if (accountUseCase.existsByEmail(request.getEmail())) {
            return BaseResponse.ofFailed(HousingErrors.EMAIL_EXIST);
        }
        return BaseResponse.ofSucceeded(accountUseCase.createUser(request));
    }

    @PostMapping("/password")
    public BaseResponse<?> setPassword(@Valid @RequestBody LoginRequest request) {
        var account = accountRepository.findByEmail(request.getEmail()).orElseThrow(UserNotFound::new);
        account.setPassword(encoder.encode(request.getPassword()));
        var current = accountRepository.save(account);
        UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(request.getEmail());
        boolean matches = encoder.matches(request.getPassword(), userDetails.getPassword());
        if (!matches) {
            throw new HousingException(HousingErrors.USER_NOT_UNAUTHORIZED);
        }
        return getBaseResponse(userDetails);
    }

    private BaseResponse<?> getBaseResponse(UserPrincipal userDetails) {
        String jwt = jwtUtils.createToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        RefreshToken refreshToken = accountUseCase.createRefreshToken(userDetails.getUuid());
        return BaseResponse.ofSucceeded(new JwtResponse(jwt,
                userDetails.getUuid(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles,
                refreshToken.getRefreshToken()));
    }

//    @PostMapping("/refreshtoken")
//    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
//        String requestRefreshToken = request.getRefreshToken();
//        return refreshTokenService.findByToken(requestRefreshToken)
//                .map(refreshTokenService::verifyExpiration)
//                .map(RefreshToken::getAccount)
//                .map(account -> {
//                    String token = jwtUtils.createToken(authenticationManager.authenticate(
//                            new UsernamePasswordAuthenticationToken(account.getEmail(), account.getPassword())));
//                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
//                })
//                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
//                        "Refresh token is not in database!"));
//    }

//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        var userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String accountId = userDetails.getUuid();
//        refreshTokenService.deleteByAccountId(accountId);
//        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
//    }
}
