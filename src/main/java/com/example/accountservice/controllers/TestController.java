package com.example.applicationgateway.controllers;

import com.example.applicationgateway.common.ServiceClient;
import com.example.applicationgateway.config.rest.BaseResponse;
import com.example.applicationgateway.dto.ReponseDto;
import com.example.applicationgateway.models.Product;
import com.example.applicationgateway.repository.ProductRepository;
import com.example.applicationgateway.security.services.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController extends ServiceClient {

    @Autowired
    private WebClient webClient;
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/all")
    public String allAccess() {
        return "Public URL";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BaseResponse<?> userAccess() {
        var user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.ofSucceeded(user);
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public BaseResponse<?> createProduct(@RequestBody Product product) {
        var user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return BaseResponse.ofSucceeded(productRepository.save(product));
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('ADMIN')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping("/forward")
    public BaseResponse<?> listOpportunities() {
        return BaseResponse.ofSucceeded(get("http://localhost:8089", ReponseDto.class, null));
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
    }
}
