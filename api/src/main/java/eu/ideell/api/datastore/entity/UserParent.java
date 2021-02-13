package eu.ideell.api.datastore.entity;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Parent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class UserParent {

  @Id
  private String userName;
  @Parent
  private Key<Department> departmentParent;

  public UserParent(final Key<Department> parent, final String userName) {
    departmentParent = parent;
    this.userName = userName;
  }
}
