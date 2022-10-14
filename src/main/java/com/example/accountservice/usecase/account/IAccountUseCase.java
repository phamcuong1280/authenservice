package com.example.accountservice.usecase.account;

import com.example.accountservice.common.GooglePojo;
import com.example.accountservice.models.Account;
import com.example.accountservice.models.RefreshToken;
import com.example.accountservice.payload.request.SignupRequest;

public interface IAccountUseCase {
    Account loginWithGoogle(GooglePojo googlePojo);

    RefreshToken createRefreshToken(String uuid);

    Account createUser(SignupRequest request);

    Boolean existsByEmail(String email);


}
