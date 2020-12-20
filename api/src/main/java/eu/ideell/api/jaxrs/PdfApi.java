package eu.ideell.api.jaxrs;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import eu.ideell.api.service.ProductPdf;

@Path("pdf")
public class PdfApi {

  @Inject
  private ProductPdf productPdf;

  @GET
  @Path("product/{customerName}/{departmentName}/{productId}")
//  @Produces({ "application/vnd.ms-excel", MediaType.APPLICATION_OCTET_STREAM })
  public Response get(@PathParam("customerName") final String customerName, @PathParam("departmentName") final String departmentName, @PathParam("productId") final long productId) throws IOException {
    return productPdf.get(customerName, departmentName, productId);
  }

}
