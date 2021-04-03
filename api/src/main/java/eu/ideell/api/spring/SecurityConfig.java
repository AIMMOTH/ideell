package eu.ideell.api.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.google.common.collect.Lists;

import se.cewebab.stockholm.util.Settings;

@Deprecated
//@EnableWebSecurity
public class SecurityConfig
//extends WebSecurityConfigurerAdapter
{

  @Value("${auth0.audience}")
  private String audience;

  @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
  private String issuer;

  @Autowired
  private Settings settings;

//  @Bean
//  SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
//    http.headers().contentSecurityPolicy("default-src 'self' " + settings.getSettingText("CSP"))
//      .reportOnly(true)
//      ;
//    return http.build();
//  }

//  @Override
//  protected void configure(final HttpSecurity http) throws Exception {
//    http.authorizeRequests()
//      .mvcMatchers(HttpMethod.GET, "**").permitAll() // GET requests don't need auth
//    .mvcMatchers(HttpMethod.GET, "/api/v1/public/**", "/index.html", "/*.js", "/*.ico", "/*.css", "/*.txt", "/assets/**").permitAll() // GET requests don't need auth
//      .anyRequest()
//      .authenticated()
//      .and()
//      .cors()
//      .configurationSource(corsConfigurationSource())
//      .and()
//      .oauth2ResourceServer()
//      .jwt()
//      .decoder(jwtDecoder())
//      .jwtAuthenticationConverter(jwtAuthenticationConverter())
//      ;
//  }

  CorsConfigurationSource corsConfigurationSource() {
    final CorsConfiguration configuration = new CorsConfiguration();
    configuration.setAllowedMethods(Lists.newArrayList(
      HttpMethod.GET.name(),
      HttpMethod.PUT.name(),
      HttpMethod.POST.name(),
      HttpMethod.DELETE.name()
    ));

    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration.applyPermitDefaultValues());
    return source;
  }

//  JwtDecoder jwtDecoder() {
//    final OAuth2TokenValidator<Jwt> withAudience = new AudienceValidator(audience);
//    final OAuth2TokenValidator<Jwt> withIssuer = JwtValidators.createDefaultWithIssuer(issuer);
//    final OAuth2TokenValidator<Jwt> validator = new DelegatingOAuth2TokenValidator<>(withAudience, withIssuer);
//
//    final NimbusJwtDecoder jwtDecoder = (NimbusJwtDecoder) JwtDecoders.fromOidcIssuerLocation(issuer);
//    jwtDecoder.setJwtValidator(validator);
//    return jwtDecoder;
//  }
//
//  JwtAuthenticationConverter jwtAuthenticationConverter() {
//    final JwtGrantedAuthoritiesConverter converter = new JwtGrantedAuthoritiesConverter();
//    converter.setAuthoritiesClaimName("permissions");
//    converter.setAuthorityPrefix("");
//
//    final JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
//    jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
//    return jwtConverter;
//  }
}
