package eu.ideell.api.datastore.entity;

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
public class Department {

  @Id
  private String departmentName;
  @Parent
  private Key<Customer> parent;
  @Index
  private final Date created = new Date();
  private String name;
  private boolean active;

  public Department(final Key<Customer> customer, final String departmentName) {
    this.departmentName = departmentName;
    this.parent = customer;
  }

}
