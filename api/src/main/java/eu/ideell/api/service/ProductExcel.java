package eu.ideell.api.service;

import java.io.IOException;
import java.util.Optional;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;

import eu.ideell.api.mongodb.SecureMongoDbImpl;
import eu.ideell.api.mongodb.entity.Product;
import se.cewebab.stockholm.excel.Excel;

public class ProductExcel {

  @Autowired
  private SecureMongoDbImpl datastore;
  @Autowired
  private Excel excel;

  public byte[] get(final String customerName, final String departmentName, final long productId) throws IOException {
    final Product product = datastore.loadUnauthorized(Product.class, productId);
    final HSSFWorkbook workbook = excel.createBookWithHeaders("Product", 5000, "RowNumber", "Name", "Category", "Description", "Cost");
    excel.addRow(workbook, 0, 1, 1, product.getName(), product.getCategory().name(), product.getDescription(), Optional.ofNullable(product.getCost()).orElse(0.0d));
    return excel.toResponse(workbook, product.getName() + ".xls");
  }
}
