package com.example.accountservice.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Account extends BaseModel {

    private String uuid;
    private String username;
    private String email;
    private String password;
    private String status = "ACTIVE";

    public Account(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
