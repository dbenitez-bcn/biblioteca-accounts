package com.example.accounts.application;

import com.example.accounts.domain.exceptions.EmailAlreadyInUse;
import com.example.accounts.domain.exceptions.InvalidEmailAddress;
import com.example.accounts.domain.exceptions.InvalidPasswordFormat;
import com.example.accounts.domain.exceptions.LoginFailed;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmailAlreadyInUse.class)
    public ResponseEntity<ErrorDetails> emailAlreadyInUse() {
        ErrorDetails errorDetails = new ErrorDetails("Email is already in use");
        return ResponseEntity.status(422).body(errorDetails);
    }

    @ExceptionHandler(InvalidEmailAddress.class)
    public ResponseEntity<ErrorDetails> invalidEmailAddress() {
        ErrorDetails errorDetails = new ErrorDetails("Invalid email address");
        return ResponseEntity.status(422).body(errorDetails);
    }

    @ExceptionHandler(InvalidPasswordFormat.class)
    public ResponseEntity<ErrorDetails> invalidPasswordFormat(InvalidPasswordFormat exception) {
        ErrorDetails errorDetails = new ErrorDetails("Invalid password format: " + exception.getMessage());
        return ResponseEntity.status(422).body(errorDetails);
    }

    @ExceptionHandler(LoginFailed.class)
    public ResponseEntity<ErrorDetails> loginFailed() {
        ErrorDetails errorDetails = new ErrorDetails("Invalid email or password");
        return ResponseEntity.status(403).body(errorDetails);
    }
}
