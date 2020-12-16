package eu.euphoros.api.service.model;

import java.util.Date;

import com.google.appengine.api.datastore.Text;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import eu.euphoros.api.datastore.entity.DepartmentParent;
import eu.euphoros.api.datastore.entity.User;
import eu.euphoros.api.type.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.cewebab.stockholm.appengine.SecureEntity;

@Cache
@Data
@Entity
@NoArgsConstructor
public class Product implements SecureEntity<DepartmentParent> {

  @Id
  private Long productId;
  @Parent
  private Key<DepartmentParent> departmentParent;
  @Index
  private Date created = new Date();
  private String creatorUserName;
  private String name;
  private Double cost;
  private Text description;
  private ProductCategory category;
  private String imageUrl;

  public Product(final User user, final ProductRequest model) {
    this.creatorUserName = user.getUserName();
    this.name = model.getName();
    this.cost = model.getCost();
    this.description = new Text(model.getDescription());
    this.category = model.getCategory();
    this.imageUrl = model.getImageUrl();
  }

  @Override
  public void setParent(final Key<DepartmentParent> parent) {
    this.departmentParent = parent;
  }

}
