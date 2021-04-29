package eu.ideell.api.security;

import java.util.Optional;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

import eu.ideell.api.util.Monad;

public class AudienceValidator implements OAuth2TokenValidator<Jwt> {

  private final String audience;

  AudienceValidator(String audience) {
      this.audience = audience;
  }

  @Override
  public OAuth2TokenValidatorResult validate(Jwt jwt) {
    return Optional.of(jwt)
        .map(Jwt::getAudience)
        .filter(value -> value.contains(audience))
        .map(__ -> OAuth2TokenValidatorResult.success())
        .orElseGet(() -> {
          return Monad.monad("The required audience is missing")
              .map(text -> new OAuth2Error("invalid_token", text, null))
              .map(error -> OAuth2TokenValidatorResult.failure(error))
              .get()
              ;
        })
        ;
  }

}
