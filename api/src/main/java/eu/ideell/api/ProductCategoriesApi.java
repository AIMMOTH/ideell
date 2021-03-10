package eu.ideell.api.jaxrs;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import eu.ideell.api.type.ProductCategory;

@Controller("public/product-categories")
public class ProductCategoriesApi {

  @GetMapping
  public ProductCategory[] get() {
    return ProductCategory.values();
  }
}
