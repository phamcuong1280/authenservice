package com.example.accountservice.controllers.mapper;

import com.example.accountservice.controllers.dto.UserDto;
import com.example.accountservice.infrastructure.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
    UserDto from(Account account);
}
