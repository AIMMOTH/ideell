package eu.ideell.api.jaxrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import eu.ideell.api.mongodb.SecureMongoDbImpl;
import eu.ideell.api.mongodb.entity.User;

public abstract class AbstractApi {

  @Autowired
  private SecureMongoDbImpl mongoDb;

  protected User getUser(final Authentication authentication) {
    return mongoDb.loadUnauthorized(User.class, authentication.getName());
  }

}
