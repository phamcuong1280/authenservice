package com.example.accountservice.payload.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String userName;

    @NotBlank
    @Size(max = 50)
    private String email;

    @NotBlank
    private String password;

    private String role;
}
