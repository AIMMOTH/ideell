package eu.ideell.api.mongodb.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import eu.ideell.api.service.model.ProductRequest;
import eu.ideell.api.type.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;
import se.cewebab.stockholm.mongodb.SecureEntity;

@Data
@Document
@NoArgsConstructor
public class Product implements SecureEntity {

  @Id
  private Long productId;
  @NonNull
  private String customerName;
  @NonNull
  private String departmentName;
  @Indexed
  private Date created = new Date();
  private String creatorUserName;
  private String name;
  private Double cost;
  private String description;
  private ProductCategory category;
  private String imageUrl;

  public Product(final User user, final ProductRequest model) {
    this.creatorUserName = user.getUserName();
    this.name = model.getName();
    this.cost = model.getCost();
    this.description = model.getDescription();
    this.category = model.getCategory();
    this.imageUrl = model.getImageUrl();
  }

}
