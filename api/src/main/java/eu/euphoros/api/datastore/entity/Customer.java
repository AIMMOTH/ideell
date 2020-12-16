package eu.euphoros.api.datastore.entity;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class Customer {

  @Id
  private String customerName;
  @Parent
  private Key<CustomerParent> parent;
  @Index
  private final Date created = new Date();
  private boolean active = true;
  private boolean invitationMandatoryForNewUsers = false;

  public Customer(final String name) {
    customerName = name;
    parent = Key.create(CustomerParent.class, name);
  }
}
