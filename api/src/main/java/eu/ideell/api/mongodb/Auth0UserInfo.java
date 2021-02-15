package eu.ideell.api.mongodb;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Auth0UserInfo {
  private String sub;
  private String givenName;
  private String familyName;
  private String nickname;
  private String picture;
  private String locale;
  private Date updatedAt;
  private String email;
  private boolean emailVerified;

  public Auth0UserInfo(final String subject, final String email) {
    sub = subject;
    this.email = email;
  }
}
