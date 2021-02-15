package eu.ideell.api.mongodb.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.ideell.api.mongodb.Auth0UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Admin {

  @Id
  private String adminName;
  @Indexed
  private final Date created = new Date();
  private final Date updated = new Date();
  private boolean active = true;
  private Auth0UserInfo userInfo;

  public Admin(final Auth0UserInfo userInfo) {
    this.adminName = userInfo.getSub();
    this.userInfo = userInfo;
  }
}
