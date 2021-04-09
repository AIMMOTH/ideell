package eu.ideell.api.spring.security;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ExceptionResponse {

  private String message;
  private String code;
}
