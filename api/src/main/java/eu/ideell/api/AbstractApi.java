package eu.ideell.api;

import org.springframework.beans.factory.annotation.Autowired;

import eu.ideell.api.mongodb.SecureMongoDbImpl;

public abstract class AbstractApi {

  @Autowired
  private SecureMongoDbImpl mongoDb;

//  protected User getUser(final Authentication authentication) {
//    return mongoDb.loadUnauthorized(User.class, authentication.getName());
//  }

}
