package com.example.accounts.domain.repositories;

import com.example.accounts.domain.aggregates.Account;
import com.example.accounts.domain.valueObjects.AccountEmail;

import java.util.Optional;

public interface AccountRepository {
    void create(Account account);
    Optional<Account> getByEmail(AccountEmail email);
}
