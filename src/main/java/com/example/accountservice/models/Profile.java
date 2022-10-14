package com.example.applicationgateway.models;


import com.example.applicationgateway.enumCommon.Gender;
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
public class Profile extends BaseModel {

    @Column(unique = true)
    private String uuid;
    private String image;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String address;

    private String accountUuid;
}
