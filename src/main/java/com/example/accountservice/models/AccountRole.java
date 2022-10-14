package com.example.applicationgateway.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AccountRole extends BaseModel {

    private String accountUuid;
    private String roleUuid;

    public AccountRole(String accountUuid, String roleUuid) {
        this.accountUuid = accountUuid;
        this.roleUuid = roleUuid;
    }
}
