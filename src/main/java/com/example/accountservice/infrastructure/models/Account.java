package com.example.accountservice.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account extends BaseModel {
    @Column(unique = true)
    private String uuid;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String validCode;
    private Boolean status = true;

    private String type;

    public Account(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Account(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
