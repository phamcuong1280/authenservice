package com.example.applicationgateway.mapper;

import com.example.applicationgateway.dto.UserDto;
import com.example.applicationgateway.models.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthControllerMapper {
    UserDto from(Account account);
}
