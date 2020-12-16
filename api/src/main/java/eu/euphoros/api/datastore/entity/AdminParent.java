package eu.euphoros.api.datastore.entity;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Cache
@Data
@Entity
@NoArgsConstructor
public class AdminParent {

  @Id
  private String adminParentName;

  public AdminParent(final String adminParentName) {
    this.adminParentName = adminParentName;
  }
}
