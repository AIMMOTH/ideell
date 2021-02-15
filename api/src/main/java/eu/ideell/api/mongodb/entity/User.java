package eu.ideell.api.mongodb.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import eu.ideell.api.mongodb.Auth0UserInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class User {

  @Id
  private String userName;
  @NonNull
  private String customerName;
  @NonNull
  private String departmentName;
  @Indexed
  private final Date created = new Date();
  private final Date updated = new Date();
  private boolean active = true;
  private Auth0UserInfo userInfo;

  public User(final String customerName, final String departmentName, final Auth0UserInfo userInfo) {
    this.customerName = customerName;
    this.userName = userInfo.getSub();
    this.departmentName = departmentName;
    this.userInfo = userInfo;
  }
}
