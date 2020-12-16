package eu.euphoros.api.factory;

import java.util.Objects;

import javax.inject.Inject;
import javax.ws.rs.core.SecurityContext;

import org.glassfish.hk2.api.Factory;

import eu.euphoros.api.jaxrs.UserPrincipal;

public class UserPrincipalFactory implements Factory<UserPrincipal> {

  @Inject
  private SecurityContext context;

  @Override
  public UserPrincipal provide() {
    if (Objects.nonNull(context.getUserPrincipal())) {
      return (UserPrincipal) context.getUserPrincipal();
    }
    return null;
  }

  @Override
  public void dispose(final UserPrincipal instance) {
    // TODO Auto-generated method stub

  }

}
