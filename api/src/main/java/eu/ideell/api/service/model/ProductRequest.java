package eu.ideell.api.service.model;

import javax.annotation.Nonnull;

import eu.ideell.api.mongodb.entity.Product;
import eu.ideell.api.type.ProductCategory;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductRequest {

  @Nonnull
  private String name;
  private Double cost;
  @Nonnull
  private String description;
  @Nonnull
  private ProductCategory category;
  private String imageUrl;

  public ProductRequest(final Product product) {
    this.name = product.getName();
    this.cost = product.getCost();
    this.description = product.getDescription();
    this.category = product.getCategory();
    this.imageUrl = product.getImageUrl();
  }

  public ProductRequest(final String name, final String description, final ProductCategory category) {
    this.name = name;
    this.description = description;
    this.category = category;
  }
}
