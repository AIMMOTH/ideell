package eu.euphoros.api.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import eu.euphoros.api.datastore.entity.User;
import se.cewebab.stockholm.appengine.SystemProperties;

public class Authorized implements SecurityContext {

  @Inject
  private SystemProperties systemProperties;
  private final UserPrincipal principal;

  public Authorized(final User user) {
    principal = new UserPrincipal(user);
  }

  @Override
  public UserPrincipal getUserPrincipal() {
    return principal;
  }

  @Override
  public boolean isUserInRole(final String role) {
    return "user".equals(role);
  }

  @Override
  public boolean isSecure() {
    return systemProperties.isProduction();
  }

  @Override
  public String getAuthenticationScheme() {
    return SecurityContext.BASIC_AUTH;
  }

}
