package com.example.applicationgateway.repository;

import com.example.applicationgateway.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
