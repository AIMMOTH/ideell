package eu.ideell.api.jaxrs;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.mongodb.entity.User;
import eu.ideell.api.service.Products;
import eu.ideell.api.service.model.ProductRequest;
import eu.ideell.api.service.model.ProductResource;

@Controller("products")
public class ProductsApi extends AbstractApi {

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
  public long post(final ProductRequest model) throws Exception {
    final User user = super.getUser(SecurityContextHolder.getContext().getAuthentication());
    return products.create(user, model);
  }

}
