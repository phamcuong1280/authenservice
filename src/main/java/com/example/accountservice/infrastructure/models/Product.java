package com.example.accountservice.infrastructure.models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Product extends BaseModel {
    private String name;


}
