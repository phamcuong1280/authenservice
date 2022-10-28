package com.example.accountservice.usecases.account;

import com.example.accountservice.common.googleDto.GooglePojo;
import com.example.accountservice.controllers.payload.request.SignupRequest;
import com.example.accountservice.infrastructure.models.Account;
import com.example.accountservice.infrastructure.models.Profile;
import org.mapstruct.Mapper;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    Account from(GooglePojo googlePojo);

    default Account from(SignupRequest request){
        var account = new Account();
        account.setUuid(UUID.randomUUID().toString());
        account.setEmail(request.getEmail());
        account.setName(request.getUserName());
        return account;
    }

    default Profile from(Account account, GooglePojo googlePojo) {
        Profile profile = new Profile();
        profile.setAccountUuid(account.getUuid());
        profile.setUuid(UUID.randomUUID().toString());
        profile.setFirstName(googlePojo.getFamily_name());
        profile.setLastName(googlePojo.getGiven_name());
        profile.setFullName(googlePojo.getName());
        profile.setEmail(googlePojo.getEmail());
        profile.setImage(googlePojo.getPicture());
        return profile;
    }
}
