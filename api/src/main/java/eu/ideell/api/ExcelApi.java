package eu.ideell.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import eu.ideell.api.service.ProductExcel;

@Controller("excel")
public class ExcelApi {

  @Autowired
  private ProductExcel productExcel;

  @GetMapping("product/{customerName}/{departmentName}/{productId}")
  public byte[] get(@PathVariable("customerName") final String customerName, @PathVariable("departmentName") final String departmentName, @PathVariable("productId") final long productId) throws IOException {
    return productExcel.get(customerName, departmentName, productId);
  }
}
