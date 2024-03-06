package com.betrybe.agrix.error;

import com.betrybe.agrix.models.entities.Farm;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralControllerAdvice {

  @ExceptionHandler(CustomError.class)
  public ResponseEntity<String> handleException(CustomError error) {
    return ResponseEntity
        .status(error.getStatus())
        .body(error.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleException(Exception error) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(error.getMessage());
  }

}
