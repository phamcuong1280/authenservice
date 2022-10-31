package com.example.accountservice.controllers;

import com.example.accountservice.common.config.rest.BaseResponse;
import com.example.accountservice.common.exception.UserNotFound;
import com.example.accountservice.common.exception.ValidCodeIncorrectException;
import com.example.accountservice.controllers.payload.request.RecoverPasswordReq;
import com.example.accountservice.usecases.account.EmailSenderService;
import com.example.accountservice.usecases.account.IAccountUseCase;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/password")
@AllArgsConstructor
public class PasswordController {

    private final IAccountUseCase accountUseCase;
    private final EmailSenderService emailSenderService;
    private final PasswordEncoder encoder;

    @GetMapping("/{email}")
    public BaseResponse<?> forgetPassword(@PathVariable String email) throws MessagingException, UnsupportedEncodingException {
        var account = accountUseCase.get(email);
        if (account == null) {
            throw new UserNotFound();
        }
        emailSenderService.sendEmail(account);
        return BaseResponse.ofSucceeded("Kiểm tra email của bạn đi bro");
    }

    @PostMapping
    public BaseResponse<?> setPassword(@Valid @RequestBody RecoverPasswordReq request) {
        var account = accountUseCase.get(request.getEmail());
        if (account == null) {
            throw new UserNotFound();
        }
        if (!Objects.equals(request.getValidCode(), account.getValidCode())) {
            throw new ValidCodeIncorrectException();
        }
        account.setPassword(encoder.encode(request.getPassword()));
        return BaseResponse.ofSucceeded(accountUseCase.update(account));
    }
}
