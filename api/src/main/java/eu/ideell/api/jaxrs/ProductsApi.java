package eu.ideell.api.jaxrs;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.core.Context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.service.Products;
import eu.ideell.api.service.model.ProductRequest;
import eu.ideell.api.service.model.ProductResource;

@Controller("products")
public class ProductsApi {

  @Autowired
  private Products products;

  @GetMapping("{customerName}/{departmentName}/{productId}")
  public ProductResource get(@PathVariable("customerName") final String customerName,
      @PathVariable("departmentName") final String departmentName,
      @PathVariable("productId") final long productId) {

    final Optional<ProductResource> optional = products.get(customerName, departmentName, productId);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      return null;
    }
  }

  @PostMapping
  @RolesAllowed({"admin", "user"})
  public long post(@Context final SecurityContext context, final ProductRequest model) {
    return products.create((UserPrincipal) context.getUserPrincipal(), model);
  }

}
