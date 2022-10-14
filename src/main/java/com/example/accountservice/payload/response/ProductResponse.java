package com.example.applicationgateway.payload.response;

import com.example.applicationgateway.web.Resource;
import lombok.Data;
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
