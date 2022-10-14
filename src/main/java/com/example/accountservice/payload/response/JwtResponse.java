package com.example.accountservice.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String uuid;
    private String username;
    private String email;
    private List<String> roles;

    private String refreshToken;

    public JwtResponse(String accessToken, String uuid, String username, String email, List<String> roles, String refreshToken) {
        this.token = accessToken;
        this.uuid = uuid;
        this.username = username;
        this.email = email;
        this.roles = roles;
        this.refreshToken = refreshToken;
    }
}
