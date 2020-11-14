package com.example.accounts.infrastructure.postgresql.implementations;

import com.example.accounts.domain.aggregates.Account;
import com.example.accounts.domain.repositories.AccountRepository;
import com.example.accounts.domain.valueObjects.AccountEmail;
import com.example.accounts.infrastructure.postgresql.entities.AccountEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PostgresAccountRepository implements AccountRepository {
    private final AccountDataSource accountDataSource;

    @Override
    public void create(Account account) {
        AccountEntity accountEntity = new AccountEntity(
                account.getId(),
                account.getEmail().getValue(),
                account.getPassword().getValue(),
                account.getRole().name()
        );
        accountDataSource.save(accountEntity);
    }

    @Override
    public Optional<Account> getByEmail(AccountEmail email) {
        Optional<AccountEntity> accountMaybe = accountDataSource.findByEmail(email.getValue());
        Optional<Account> account = accountMaybe.map(accountEntity -> new Account(
                accountMaybe.get().getId(),
                accountMaybe.get().getEmail(),
                accountMaybe.get().getPassword(),
                accountMaybe.get().getRole()
        ));

        return account;
    }
}
