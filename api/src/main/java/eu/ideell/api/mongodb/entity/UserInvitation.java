package eu.ideell.api.mongodb.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class UserInvitation {

  @Id
  private String email;
  @Indexed
  private Date created = new Date();

  public UserInvitation(final String invitedEmailAddress) {
    email = invitedEmailAddress;
  }
}
