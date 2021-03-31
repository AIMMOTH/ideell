package eu.ideell.api;

import java.security.Principal;

import eu.ideell.api.mongodb.entity.User;
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
