package eu.euphoros.api.jaxrs;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import eu.euphoros.api.service.ProductExcel;

@Path("excel")
public class ExcelApi {

  @Inject
  private ProductExcel productExcel;

  @GET
  @Path("product/{customerName}/{departmentName}/{productId}")
  @Produces({ "application/vnd.ms-excel", MediaType.APPLICATION_OCTET_STREAM })
  public Response get(@PathParam("customerName") final String customerName, @PathParam("departmentName") final String departmentName, @PathParam("productId") final long productId) throws IOException {
    return productExcel.get(customerName, departmentName, productId);
  }
}
