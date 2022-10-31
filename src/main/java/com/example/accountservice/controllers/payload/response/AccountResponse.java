package com.example.accountservice.controllers.payload.response;

import lombok.Data;

@Data
public class AccountResponse {
    private String uuid;
    private String name;
    private String email;
    private Boolean status;
    private String type;
}
