package com.example.accountservice.usecases.account;

import com.example.accountservice.common.googleDto.GooglePojo;
import com.example.accountservice.controllers.payload.request.SignupRequest;
import com.example.accountservice.infrastructure.models.Account;
import com.example.accountservice.infrastructure.models.RefreshToken;

public interface IAccountUseCase {
    Account loginWithGoogle(GooglePojo googlePojo);

    RefreshToken createRefreshToken(String uuid);

    Account createUser(SignupRequest request);

    Boolean existsByEmail(String email);

    Account update(Account account);

    Account get(String email);

}
