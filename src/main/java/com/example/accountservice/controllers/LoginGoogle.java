package com.example.accountservice.controllers;

import com.example.accountservice.common.GooglePojo;
import com.example.accountservice.common.GoogleUtils;
import com.example.accountservice.config.rest.BaseResponse;
import com.example.accountservice.exception.HousingErrors;
import com.example.accountservice.mapper.AuthControllerMapper;
import com.example.accountservice.repository.AccountRepository;
import com.example.accountservice.security.jwt.JwtUtils;
import com.example.accountservice.usecase.account.IAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
public class LoginGoogle {
    private final IAccountUseCase accountUseCase;
    private final AuthControllerMapper mapper;
    private AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;
    private PasswordEncoder encoder;
    private UserDetailsService service;
    private JwtUtils jwtUtils;
    private GoogleUtils googleUtils;

    @RequestMapping("/login-google")
    public BaseResponse<?> loginGoogle(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return BaseResponse.ofFailed(HousingErrors.BAD_GATEWAY);
        }
        String accessToken = googleUtils.getToken(code);
        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        return BaseResponse.ofSucceeded(accountUseCase.loginWithGoogle(googlePojo));
    }


    @GetMapping("/login-with-google")
    RedirectView redirectWithRedirectAttributes() {
        return new RedirectView("https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=http://localhost:8088/login-google&response_type=code&client_id=85998077224-t35s1f5h11lvf1nkdbm448dselumfs5t.apps.googleusercontent.com&approval_prompt=force");
    }
}
