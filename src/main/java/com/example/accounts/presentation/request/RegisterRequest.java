package com.example.accounts.presentation.request;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RegisterRequest {
    public final String email;
    public final String password;
}
