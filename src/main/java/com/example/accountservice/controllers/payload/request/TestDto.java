package com.example.accountservice.controllers.payload.request;

import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class TestDto {
    @Pattern(regexp = "^(YES|NO)$", message = "Accept YES, NO")
    String type;
    String name = "abc";
    String address;
}
