package com.example.accountservice.controllers;

import com.example.accountservice.common.web.Resource;
import com.example.accountservice.common.web.ServiceClient;
import com.example.accountservice.controllers.payload.request.ProductCreateRequest;
import com.example.accountservice.controllers.payload.response.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/v1/products")
public class ProductController extends ServiceClient {
    private final String url;
    private final String basicAuth;

    @Autowired
    public ProductController(
            @Value("${spring.service.product-service.products.url}") String url,
            @Value("${spring.service.product-service.basic-auth}") String basicAuth) {
        this.url = url;
        this.basicAuth = basicAuth;

    }

    @PostMapping
    public Resource<?> create(@RequestBody ProductCreateRequest request) {
        return new Resource<>(post(url, request, ProductCreateRequest.class, ProductResponse.class));
//        var responseType = new ParameterizedTypeReference<BaseResponse>() {};
//        HttpHeaders httpHeaders = RestUtils.createHeadersWithBasicAuth("john123", "password");
//         var monoResp = webClient.get()
//                 .uri("http://localhost:8089")
//                 .accept(MediaType.APPLICATION_JSON)
//                 .headers(a -> a.setBasicAuth("john123", "password"))
//                 .retrieve()
//                 .bodyToMono(responseType);
//
//        var resp = monoResp.block();
//        return BaseResponse.ofSucceeded(resp.getData());
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setBasicAuth("user", "password");
//        var body = new HttpEntity<>(request, headers);
//        var responseType = new ParameterizedTypeReference<ProductResponse>() {
//        };
//        var response = restTemplate.exchange(
//                "http://product-service/v1/products",
//                HttpMethod.POST,
//                body,
//                responseType).getBody();
    }


}
