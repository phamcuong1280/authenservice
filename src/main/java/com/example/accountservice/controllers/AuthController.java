package com.example.accountservice.controllers;

import com.example.accountservice.common.config.rest.BaseResponse;
import com.example.accountservice.common.exception.HousingErrors;
import com.example.accountservice.common.exception.UserNotFound;
import com.example.accountservice.common.exception.constant.HousingException;
import com.example.accountservice.common.security.jwt.JwtUtils;
import com.example.accountservice.common.security.services.UserDetailsServiceImpl;
import com.example.accountservice.common.security.services.UserPrincipal;
import com.example.accountservice.controllers.payload.request.LoginRequest;
import com.example.accountservice.controllers.payload.request.SignupRequest;
import com.example.accountservice.controllers.payload.response.JwtResponse;
import com.example.accountservice.infrastructure.repository.JpaAccountRepository;
import com.example.accountservice.usecases.account.IAccountUseCase;
import com.example.accountservice.usecases.token.ITokenUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
    private final UserDetailsServiceImpl userDetailsService;
    private final JpaAccountRepository jpaAccountRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;
    private final IAccountUseCase accountUseCase;
    private final ITokenUseCase tokenUseCase;

    @PostMapping("/signin")
    public BaseResponse<?> authenticateUser(@Valid @RequestBody LoginRequest request) {
        UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(request.getEmail());
        if (userDetails.getUuid() == null) {
            return BaseResponse.ofFailed(HousingErrors.USER_NOT_UNAUTHORIZED);
        }
        boolean matches = encoder.matches(request.getPassword(), userDetails.getPassword());
        if (userDetails.getUuid() == null || !matches) {
            return BaseResponse.ofFailed(HousingErrors.USER_NOT_UNAUTHORIZED);
        }
        jpaAccountRepository.login(userDetails.getUuid());
        return getBaseResponse(userDetails);
    }


    @GetMapping("/{email}")
    public BaseResponse<?> getByEmail(@PathVariable String email) {
        return BaseResponse.ofSucceeded(jpaAccountRepository.findByEmailAndStatusIsTrue(email));
    }


    @PostMapping("/signup")
    public BaseResponse<?> registerUser(@Valid @RequestBody SignupRequest request) {
        if (accountUseCase.existsByEmail(request.getEmail())) {
            return BaseResponse.ofFailed(HousingErrors.EMAIL_EXIST);
        }
        var account = accountUseCase.createUser(request);

        return BaseResponse.ofSucceeded(account);
    }

    @PostMapping("/password")
    public BaseResponse<?> setPassword(@Valid @RequestBody LoginRequest request) {
        var account = jpaAccountRepository.findByEmailAndStatusIsTrue(request.getEmail()).orElseThrow(UserNotFound::new);
        account.setPassword(encoder.encode(request.getPassword()));
        jpaAccountRepository.save(account);
        UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(request.getEmail());
        boolean matches = encoder.matches(request.getPassword(), userDetails.getPassword());
        if (!matches) {
            throw new HousingException(HousingErrors.USER_NOT_UNAUTHORIZED);
        }
        return getBaseResponse(userDetails);
    }

    private BaseResponse<?> getBaseResponse(UserPrincipal userDetails) {
        return BaseResponse.ofSucceeded(new JwtResponse(jwtUtils.createToken(userDetails)));
    }

    @GetMapping("/token/{refreshToken}")
    public BaseResponse<?> test(@PathVariable String refreshToken) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.ofSucceeded(tokenUseCase.getToken(userPrincipal.getUuid()));
    }

    @GetMapping("/signout")
    public BaseResponse<?> logoutUser() {
        var userDetails = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        jpaAccountRepository.logout(userDetails.getUuid());
        return BaseResponse.ofSucceeded("logout success");
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


}
