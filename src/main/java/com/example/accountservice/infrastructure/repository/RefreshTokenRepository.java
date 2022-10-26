package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
}
