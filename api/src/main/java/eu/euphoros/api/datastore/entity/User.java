package eu.euphoros.api.datastore.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import eu.euphoros.api.datastore.Auth0UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class User {

  @Id
  private String userName;
  @Parent
  private Key<UserParent> userParent;
  @Index
  private final Date created = new Date();
  private final Date updated = new Date();
  private boolean active = true;
  private Auth0UserInfo userInfo;

  public User(final Key<UserParent> key, final Auth0UserInfo userInfo) {
    this.userName = key.getName();
    this.userParent = key;
    this.userInfo = userInfo;
  }
}
