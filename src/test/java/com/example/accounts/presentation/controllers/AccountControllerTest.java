package com.example.accounts.presentation.controllers;

import com.example.accounts.application.AccountService;
import com.example.accounts.domain.aggregates.Account;
import com.example.accounts.presentation.request.LoginRequest;
import com.example.accounts.presentation.request.RegisterRequest;
import com.example.accounts.presentation.responses.LoginResponse;
import com.example.accounts.utils.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;

import static com.example.accounts.fixtures.AccountFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AccountControllerTest {
    @Mock
    private AccountService accountService;
    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AccountController sut;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void register_shouldRegisterAnAccount() {
        RegisterRequest request = new RegisterRequest(ACCOUNT_EMAIL, ACCOUNT_PASSWORD);

        sut.register(request);

        verify(accountService).register(ACCOUNT_EMAIL, ACCOUNT_PASSWORD);
    }

    @Test
    void login_shouldLoginAnAccount() {
        String aToken = "A_TOKEN";
        Account account = defaultAccount();
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", account.getRole().name());
        when(jwtUtils.generateToken(account.getId().toString(), claims)).thenReturn(aToken);
        when(accountService.login(ACCOUNT_EMAIL, ACCOUNT_PASSWORD)).thenReturn(account);

        LoginResponse result = sut.login(new LoginRequest(ACCOUNT_EMAIL, ACCOUNT_PASSWORD));

        assertThat(result.token).isEqualTo(aToken);
    }
}