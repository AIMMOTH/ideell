package eu.euphoros.api.jaxrs;

import java.security.Principal;

import eu.euphoros.api.datastore.entity.User;
import lombok.Data;

@Data
public class UserPrincipal implements Principal {

  private final User user;

  public UserPrincipal(final User user) {
    this.user = user;
  }

  @Override
  public String getName() {
    return user.getUserInfo().getSub();
  }

}
