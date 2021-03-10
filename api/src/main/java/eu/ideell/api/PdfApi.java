package eu.ideell.api.jaxrs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import eu.ideell.api.service.ProductPdf;

@Controller("pdf")
public class PdfApi {

  @Autowired
  private ProductPdf productPdf;

  @GetMapping("product/{customerName}/{departmentName}/{productId}")
  public byte[] get(@PathVariable("customerName") final String customerName, @PathVariable("departmentName") final String departmentName, @PathVariable("productId") final long productId) throws IOException {
    return productPdf.get(customerName, departmentName, productId);
  }

}
