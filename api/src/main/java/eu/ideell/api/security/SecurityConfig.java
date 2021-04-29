package eu.ideell.api.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

  @Value("${auth0.audience}")
  private String audience;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Value("${cors}")
  private String cors;

  @Value("${csp}")
  private String csp;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .mvcMatchers("/api/v1/public/**").permitAll()
      .mvcMatchers("/api/v1/private/**").authenticated()
      .and().cors().configurationSource(corsConfigurationSource())
//      .and().csrf().disable()
      .and().oauth2ResourceServer().jwt()
      ;
  }

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedMethods("*");
  }

  @Bean
  JwtDecoder jwtDecoder() {
      NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);

      OAuth2TokenValidator<Jwt> audienceValidator = new AudienceValidator(audience);
      OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
      OAuth2TokenValidator<Jwt> withAudience = new DelegatingOAuth2TokenValidator<>(withIssuer, audienceValidator);

      jwtDecoder.setJwtValidator(withAudience);

      return jwtDecoder;
  }

  private CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedMethods(Arrays.asList(
      HttpMethod.GET.name(),
      HttpMethod.PUT.name(),
      HttpMethod.POST.name(),
      HttpMethod.DELETE.name()
    ));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
    return source;
  }

//  @Bean
//  SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
//    http.headers().contentSecurityPolicy("default-src 'self' " + csp)
//      .reportOnly(true)
//      ;
//    return http.build();
//  }
}
