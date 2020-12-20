package eu.ideell.api.jaxrs;

import java.util.Optional;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import eu.ideell.api.service.Products;
import eu.ideell.api.service.model.ProductRequest;
import eu.ideell.api.service.model.ProductResource;

@Path("products")
public class ProductsApi {

  @Inject
  private Products products;

  @GET
  @Path("{customerName}/{departmentName}/{productId}")
  @Produces(MediaType.APPLICATION_JSON)
  public ProductResource get(@PathParam("customerName") final String customerName,
      @PathParam("departmentName") final String departmentName,
      @PathParam("productId") final long productId) {

    final Optional<ProductResource> optional = products.get(customerName, departmentName, productId);
    if (optional.isPresent()) {
      return optional.get();
    } else {
      return null;
    }
  }

  @POST
  @RolesAllowed({"admin", "user"})
  public long post(@Context final SecurityContext context, final ProductRequest model) {
    return products.create((UserPrincipal) context.getUserPrincipal(), model);
  }

}
