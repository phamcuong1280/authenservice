package com.example.accountservice.controllers.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JwtResponse {
    private String accessToken;
    private String type = "Bearer";
    private String uuid;
    private String username;
    private String email;
    private List<String> roles;

    private String refreshToken;

    public JwtResponse(String accessToken, String uuid, String username, String email, List<String> roles) {
        this.accessToken = accessToken;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
