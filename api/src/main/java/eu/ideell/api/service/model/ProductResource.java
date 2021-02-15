package eu.ideell.api.service.model;

import javax.annotation.Nonnull;

import eu.ideell.api.mongodb.entity.Product;
import eu.ideell.api.type.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductResource {

  @Nonnull
  private String customer;
  @Nonnull
  private String department;
  @Nonnull
  private long productId;
  @Nonnull
  private String name;
  private Double cost;
  @Nonnull
  private String description;
  @Nonnull
  private ProductCategory category;
  private String imageUrl;

  public ProductResource(final Product product) {
//    this.customer = departmentParent.getParent().getName();
    this.department = product.getDepartmentName();
    this.productId = product.getProductId();
    this.name = product.getName();
    this.cost = product.getCost();
    this.description = product.getDescription();
    this.category = product.getCategory();
    this.imageUrl = product.getImageUrl();
  }
}
