package com.example.accountservice.infrastructure.repository;

import com.example.accountservice.infrastructure.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface JpaAccountRepository extends JpaRepository<Account, Long> {

    Boolean existsByEmail(String email);

    Optional<Account> findByEmailAndStatusIsTrue(String email);

    Optional<Account> findByEmail(String email);

    @Query(value = "UPDATE Account set password = ?2, updateTime = current_timestamp where email =?1")
    Account setPassword(String email, String password);

    @Transactional
    @Async
    @Modifying
    @Query(value = "UPDATE Account set status = false where uuid = ?1")
    void logout(String uuid);

    @Transactional
    @Modifying
    @Async
    @Query(value = "UPDATE Account set status = true where uuid = ?1")
    void login(String uuid);
}
