package com.example.accounts.application;

import com.example.accounts.domain.aggregates.Account;
import com.example.accounts.domain.exceptions.EmailAlreadyInUse;
import com.example.accounts.domain.exceptions.LoginFailed;
import com.example.accounts.domain.repositories.AccountRepository;
import com.example.accounts.domain.valueObjects.AccountEmail;
import com.example.accounts.domain.valueObjects.PlainPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordManager passwordManager;

    public void register(String email, String password) {
        Optional<Account> accountMaybe = accountRepository.getByEmail(new AccountEmail(email));
        accountMaybe.ifPresent((account) -> {
            throw new EmailAlreadyInUse();
        });
        String hashedPassword = passwordManager.encode(new PlainPassword(password));
        Account account = new Account(email, hashedPassword);
        accountRepository.create(account);
    }

    public Account login(String email, String password) {
        Optional<Account> accountMaybe = accountRepository.getByEmail(new AccountEmail(email));
        if (accountMaybe.isPresent()) {
            Account account = accountMaybe.get();
            boolean passwordMatches = passwordManager
                    .matches(
                            new PlainPassword(password),
                            account.getPassword()
                    );
            if (passwordMatches) {
                return account;
            }
        }
        throw new LoginFailed();
    }
}
