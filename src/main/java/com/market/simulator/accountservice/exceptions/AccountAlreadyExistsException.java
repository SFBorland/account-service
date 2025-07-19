package com.market.simulator.accountservice.exceptions;

public class AccountAlreadyExistsException extends RuntimeException {

  public AccountAlreadyExistsException() {
    super("Account already exists");
  }

  public AccountAlreadyExistsException(String message) {
    super(message);
  }
}
