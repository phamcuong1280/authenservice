package com.example.accountservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String uuid;
    private String username;
    private String email;
    private List<String> roles;
}
