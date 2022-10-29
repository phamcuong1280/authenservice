package com.example.accountservice.controllers.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RecoverPasswordReq {
    @NotBlank
    private String validCode;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
