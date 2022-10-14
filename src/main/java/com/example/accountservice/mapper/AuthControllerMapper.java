package com.example.accountservice.mapper;

import com.example.accountservice.dto.UserDto;
import com.example.accountservice.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
    UserDto from(Account account);
}
