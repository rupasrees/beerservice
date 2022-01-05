package com.example.beerservice.exception;

import javax.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<Object> notFoundException(EntityNotFoundException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
  }

  @ExceptionHandler(value = ValidationException.class)
  public ResponseEntity<Object> validationException(ValidationException exception) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
