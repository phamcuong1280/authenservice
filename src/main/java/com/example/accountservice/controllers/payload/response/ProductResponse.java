package com.example.accountservice.controllers.payload.response;

import com.example.accountservice.common.web.Resource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponse extends Resource<ProductResponse> {
    private String uuid;
    private String name;
    private Double price;
    private Double discount;
    private Long count;
}
