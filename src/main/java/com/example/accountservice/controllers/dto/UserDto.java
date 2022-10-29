package com.example.accountservice.controllers.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String uuid;
    private String name;
    private String email;
    private List<String> roles;
}
