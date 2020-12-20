package eu.ideell.api.jaxrs;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import eu.ideell.api.type.ProductCategory;

@Path("public/product-categories")
public class ProductCategoriesApi {

  @GET
  public ProductCategory[] get() {
    return ProductCategory.values();
  }
}
