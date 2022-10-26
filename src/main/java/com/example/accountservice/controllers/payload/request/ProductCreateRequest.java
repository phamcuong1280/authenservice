package com.example.accountservice.controllers.payload.request;

import lombok.Data;

@Data
public class ProductCreateRequest {
    private String uuid;
    private String name;
    private Double price;
    private Double discount;
    private Long count;
}
