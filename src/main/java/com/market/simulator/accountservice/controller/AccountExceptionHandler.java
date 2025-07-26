package com.market.simulator.accountservice.controller;

import com.market.simulator.accountservice.exceptions.AccountAlreadyExistsException;
import com.market.simulator.accountservice.exceptions.AccountNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class AccountExceptionHandler {

  @ExceptionHandler(AccountNotFoundException.class)
  public ResponseEntity<String> handleAccountNotFoundException(AccountNotFoundException ex) {
    log.warn("Account not found: {}", ex.getMessage());
    String message = ex.getMessage() != null && !ex.getMessage().trim().isEmpty() ? ex.getMessage() : "Account not found";
    return ResponseEntity
        .status(HttpStatus.NOT_FOUND)
        .body(message);
  }

  @ExceptionHandler(AccountAlreadyExistsException.class)
  public ResponseEntity<String> handleAccountAlreadyExistsException(AccountAlreadyExistsException ex) {
    log.warn("Account already exists: {}", ex.getMessage());
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(ex.getMessage());
  }
}
