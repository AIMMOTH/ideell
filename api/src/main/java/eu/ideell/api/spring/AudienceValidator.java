package eu.ideell.api.spring;

import org.springframework.util.Assert;

@Deprecated
public class AudienceValidator
//implements OAuth2TokenValidator<Jwt>
{

  private final String audience;

  AudienceValidator(final String audience) {
    Assert.hasText(audience, "audience is null or empty");
    this.audience = audience;
  }

//  @Override
//  public OAuth2TokenValidatorResult validate(final Jwt jwt) {
//    final List<String> audiences = jwt.getAudience();
//    if (audiences.contains(this.audience)) {
//      return OAuth2TokenValidatorResult.success();
//    }
//    final OAuth2Error err = new OAuth2Error(OAuth2ErrorCodes.INVALID_TOKEN);
//    return OAuth2TokenValidatorResult.failure(err);
//  }

}
