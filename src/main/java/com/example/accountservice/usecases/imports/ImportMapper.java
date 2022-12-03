package com.example.accountservice.usecases.imports;

import com.example.accountservice.infrastructure.models.Account;
import com.example.accountservice.infrastructure.models.Profile;
import com.example.accountservice.usecases.model.AgentCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ImportMapper {

    Account toAccount(AgentCreateDto dto);

    @Mapping(target = "firstName", source = "name")
    @Mapping(target = "lastName", source = "lastMiddleName")
    Profile toProfile(AgentCreateDto dto);

}
