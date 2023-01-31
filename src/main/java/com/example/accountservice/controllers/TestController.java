package com.example.accountservice.controllers;

import com.example.accountservice.common.security.services.UserPrincipal;
import com.example.accountservice.common.web.ServiceClient;
import com.example.accountservice.controllers.payload.request.TestDto;
import com.example.accountservice.infrastructure.models.Product;
import com.example.accountservice.infrastructure.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController extends ServiceClient {

    @Autowired
    private JpaAccountRepository jpaAccountRepository;
    @Autowired
    private JpaRoleRepository jpaRoleRepository;
    @Autowired
    private JpaAccountRoleRepository jpaAccountRoleRepository;
    @Autowired
    private JpaProfileRepository jpaProfileRepository;


    @Autowired
    private WebClient webClient;
    @Autowired
    private JpaProductRepository jpaProductRepository;


    @GetMapping("/all")
    public String allAccess() {
        return "Public URL";
    }

    @PostMapping("/all")
    public ResponseEntity<?> test(@Valid @RequestBody TestDto testDto) {
        return ResponseEntity.ok(testDto);
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> userAccess() {
        var user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(user);
    }

    @PostMapping("/products")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> createProduct(@RequestBody Product product) {
        var user = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(jpaProductRepository.save(product));
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
        jpaAccountRoleRepository.deleteAll();
        jpaRoleRepository.deleteAll();
        jpaProfileRepository.deleteAll();
        jpaAccountRepository.deleteAll();
        return "Delete success";
    }
}
