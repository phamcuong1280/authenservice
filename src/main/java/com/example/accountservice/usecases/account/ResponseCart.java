package com.example.accountservice.usecases.account;

import com.example.accountservice.common.web.Resource;
import lombok.Data;

@Data
public class ResponseCart extends Resource<ResponseCart> {
    String string;
}
