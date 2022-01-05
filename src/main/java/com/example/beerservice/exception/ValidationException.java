package com.example.beerservice.exception;

public class ValidationException extends Exception {

  public ValidationException() {
    super();
  }

  public ValidationException(final String message) {
    super(message);
  }

  public ValidationException(final String message, final Throwable cause) {
    super(message, cause);
  }


}
