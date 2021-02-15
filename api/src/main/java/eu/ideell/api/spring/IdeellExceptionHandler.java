package eu.ideell.api.spring;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IdeellExceptionHandler {

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ExceptionResponse> unauthorized(final UnauthorizedException e) {
    return new ResponseEntity<ExceptionResponse>(new ExceptionResponse("Unauthorized", "401"), HttpStatus.UNAUTHORIZED);
  }
}
