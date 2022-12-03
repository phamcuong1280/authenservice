package com.example.accountservice.usecases.model;

import lombok.Data;

@Data
public class AgentCreateDto {
    private String fullName;
    private String name;
    private String lastMiddleName;
    private String phone;
    private String email;
    private String branch;
    private String agentCode;

}
