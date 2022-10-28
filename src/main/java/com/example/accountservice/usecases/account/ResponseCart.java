package com.example.accountservice.usecases.account;

import com.example.accountservice.common.web.Resource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseCart extends Resource<ResponseCart> {
    String string;
}
