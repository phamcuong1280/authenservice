package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProfileRepository extends JpaRepository<Profile, Long> {
}
