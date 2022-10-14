package com.example.accountservice.repository;

import com.example.accountservice.models.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Long> {
    List<AccountRole> findAllByAccountUuid(String accountUuid);
}
