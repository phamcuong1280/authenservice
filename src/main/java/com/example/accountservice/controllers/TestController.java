package com.example.accountservice.controllers;

import com.example.accountservice.common.config.rest.BaseResponse;
import com.example.accountservice.common.security.services.UserPrincipal;
import com.example.accountservice.common.web.ServiceClient;
import com.example.accountservice.infrastructure.models.Product;
import com.example.accountservice.infrastructure.repository.*;
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
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRoleRepository accountRoleRepository;
    @Autowired
    private ProfileRepository profileRepository;


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
    public String createAccount() {
        return "Moderator Board.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('USER')")
    public String createProduct() {
        return "Admin Board.";
    }

    @GetMapping("/delete")
    public String delete() {
        accountRoleRepository.deleteAll();
        roleRepository.deleteAll();
        profileRepository.deleteAll();
        accountRepository.deleteAll();
        return "Delete success";
    }
}
