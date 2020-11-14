package com.example.accounts.infrastructure.postgresql.implementations;

import com.example.accounts.infrastructure.postgresql.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AccountDataSource extends JpaRepository<AccountEntity, UUID> {

    Optional<AccountEntity> findByEmail(String email);
}
