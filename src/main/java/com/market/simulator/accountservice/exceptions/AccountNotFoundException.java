package com.market.simulator.accountservice.exceptions;

public class AccountNotFoundException extends RuntimeException {

  public AccountNotFoundException() {
    super("User already exists");
  }

  public AccountNotFoundException(String message) {
    super(message);
  }
}
