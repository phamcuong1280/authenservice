package com.example.accountservice.controllers.mapper;

import com.example.accountservice.controllers.payload.response.AccountResponse;
import com.example.accountservice.infrastructure.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
    AccountResponse from(Account account);
}
