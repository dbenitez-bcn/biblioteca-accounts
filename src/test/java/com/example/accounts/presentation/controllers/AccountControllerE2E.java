package com.example.accounts.presentation.controllers;

import com.example.accounts.utils.ApplicationTestCase;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.springframework.http.HttpMethod.GET;

class AccountControllerE2E extends ApplicationTestCase {
    @Test
    void foo_fee() throws Exception {
        JSONObject body = new JSONObject();
        body.put("email", "admin@biblioteca.com");
        body.put("password", "password1234");

        assertRequestWithBody(GET, "/v1/login", body, 200);
    }
}