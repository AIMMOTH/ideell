package eu.ideell.api.datastore.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import eu.ideell.api.datastore.Auth0UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class Admin {

  @Id
  private String adminName;
  @Parent
  private Key<AdminParent> adminParent;
  @Index
  private final Date created = new Date();
  private final Date updated = new Date();
  private boolean active = true;
  private Auth0UserInfo userInfo;

  public Admin(final Key<AdminParent> adminParent, final Auth0UserInfo userInfo) {
    this.adminName = adminParent.getName();
    this.adminParent = adminParent;
    this.userInfo = userInfo;
  }
}
