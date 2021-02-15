package eu.ideell.api.mongodb.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Customer {

  @Id
  private String customerName;
  private final Date created = new Date();
  private boolean active = true;
  private boolean invitationMandatoryForNewUsers = false;

  public Customer(final String name) {
    customerName = name;
  }
}
