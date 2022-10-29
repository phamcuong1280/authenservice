package com.example.accountservice.infrastructure.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseModel {
    @Column(unique = true)
    private String uuid;
    private String name;
    @Column(unique = true)
    private String email;
    private String password;
    private String validCode;
    private Boolean status = true;

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
