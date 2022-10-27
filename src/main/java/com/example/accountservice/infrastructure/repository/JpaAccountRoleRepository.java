package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaAccountRoleRepository extends JpaRepository<AccountRole, Long> {
    List<AccountRole> findAllByAccountUuid(String accountUuid);
}
