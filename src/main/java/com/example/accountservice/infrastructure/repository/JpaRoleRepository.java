package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRole(String role);

    List<Role> findByUuidIn(List<String> uuid);
}
