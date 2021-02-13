package eu.ideell.api.datastore.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class Customer {

  @Id
  private String customerName;
  @Index
  private final Date created = new Date();
  private boolean active = true;
  private boolean invitationMandatoryForNewUsers = false;

  public Customer(final String name) {
    customerName = name;
  }
}
