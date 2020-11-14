package com.example.accounts.presentation.controllers;

import com.example.accounts.application.AccountService;
import com.example.accounts.domain.aggregates.Account;
import com.example.accounts.presentation.request.LoginRequest;
import com.example.accounts.presentation.request.RegisterRequest;
import com.example.accounts.presentation.responses.LoginResponse;
import com.example.accounts.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
public class AccountController {
    private final AccountService accountService;
    private final JwtUtils jwtUtils;

    @PostMapping("/v1/register")
    public void register(@RequestBody RegisterRequest requestVM) {
        accountService.register(requestVM.email, requestVM.password);
    }

    @GetMapping("/v1/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        Account account = accountService.login(request.email, request.password);
        HashMap<String, Object> claims = new HashMap<>();
        claims.put("role", account.getRole().name());
        String token = jwtUtils.generateToken(account.getId().toString(), claims);

        return new LoginResponse(token);
    }
}
