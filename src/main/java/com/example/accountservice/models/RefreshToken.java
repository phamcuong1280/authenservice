package com.example.applicationgateway.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
@Entity(name = "refresh_token")
public class RefreshToken extends BaseModel {

    private String accountUuid;
    private String refreshToken;

}
