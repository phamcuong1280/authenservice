package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
