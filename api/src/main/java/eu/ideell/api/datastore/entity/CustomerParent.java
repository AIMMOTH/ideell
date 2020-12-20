package eu.ideell.api.datastore.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class CustomerParent {

  @Id
  private String customerName;

  public CustomerParent(final String name) {
    customerName = name;
  }
}
