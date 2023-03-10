package com.example.accountservice.controllers;

import com.example.accountservice.common.exception.MyErrors;
import com.example.accountservice.common.googleDto.GooglePojo;
import com.example.accountservice.common.googleDto.GoogleUtils;
import com.example.accountservice.common.security.jwt.JwtUtils;
import com.example.accountservice.common.security.services.UserDetailsServiceImpl;
import com.example.accountservice.common.security.services.UserPrincipal;
import com.example.accountservice.controllers.mapper.AuthControllerMapper;
import com.example.accountservice.controllers.payload.response.JwtResponse;
import com.example.accountservice.infrastructure.models.TypeAccount;
import com.example.accountservice.usecases.account.IAccountUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
@Slf4j
public class LoginGoogleController {
    private final IAccountUseCase accountUseCase;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthControllerMapper mapper;
    private PasswordEncoder encoder;
    private JwtUtils jwtUtils;
    private GoogleUtils googleUtils;

    @RequestMapping("/login-google")
    public ResponseEntity<?> loginGoogle(HttpServletRequest request) throws IOException {
        String code = request.getParameter("code");

        if (code == null || code.isEmpty()) {
            return ResponseEntity.badRequest().body(MyErrors.BAD_GATEWAY);
        }
        String accessToken = googleUtils.getToken(code);
        GooglePojo googlePojo = googleUtils.getUserInfo(accessToken);
        log.info("[LoginGoogleController][loginGoogle]: google pojo - " + googlePojo);
        var account = accountUseCase.get(googlePojo.getEmail());
        if (account != null && account.getType().equals(TypeAccount.google)) {
            UserPrincipal userDetails = (UserPrincipal) userDetailsService.loadUserByUsername(account.getEmail());
            return ResponseEntity.ok(new JwtResponse(jwtUtils.createToken(userDetails)));
        }
        return ResponseEntity.ok(mapper.from(accountUseCase.loginWithGoogle(googlePojo)));
    }


    @GetMapping("/login-with-google")
    RedirectView redirectWithRedirectAttributes() {
        return new RedirectView("https://accounts.google.com/o/oauth2/auth?scope=profile email&redirect_uri=https://account-service-production.up.railway.app/login-google&response_type=code&client_id=85998077224-t35s1f5h11lvf1nkdbm448dselumfs5t.apps.googleusercontent.com&approval_prompt=force");
    }
}
