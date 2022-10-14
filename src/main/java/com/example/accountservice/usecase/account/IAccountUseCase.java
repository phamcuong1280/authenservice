package com.example.accountservice.account;

import com.example.applicationgateway.common.GooglePojo;
import com.example.applicationgateway.models.Account;
import com.example.applicationgateway.models.RefreshToken;
import com.example.applicationgateway.payload.request.SignupRequest;

public interface IAccountUseCase {
    Account loginWithGoogle(GooglePojo googlePojo);

    RefreshToken createRefreshToken(String uuid);

    Account createUser(SignupRequest request);

    Boolean existsByEmail(String email);


}
