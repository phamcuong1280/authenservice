package com.example.accountservice.config.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
public class FieldViolation {
    private String field;
    private String description;
}

