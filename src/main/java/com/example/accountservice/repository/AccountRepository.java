package com.example.applicationgateway.repository;

import com.example.applicationgateway.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<Account> findByEmail(String email);

    @Query(value = "UPDATE Account set password = ?2, updateTime = current_timestamp where email =?1")
    Account setPassword(String email, String password);


}
