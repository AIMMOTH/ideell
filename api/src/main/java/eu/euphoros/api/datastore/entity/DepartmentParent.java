package eu.euphoros.api.datastore.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class DepartmentParent {

  @Id
  private String departmentName;
  private Key<CustomerParent> customerParent;

  public DepartmentParent(final Key<CustomerParent> customer, final String departmentName) {
    this.departmentName = departmentName;
    customerParent = customer;
  }

}
