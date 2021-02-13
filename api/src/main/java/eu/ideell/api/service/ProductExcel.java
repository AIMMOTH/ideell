package eu.ideell.api.service;

import java.io.IOException;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.googlecode.objectify.Key;

import eu.ideell.api.datastore.SecureDatastoreImpl;
import eu.ideell.api.datastore.entity.Customer;
import eu.ideell.api.datastore.entity.Department;
import eu.ideell.api.service.model.Product;
import se.cewebab.stockholm.excel.Excel;

public class ProductExcel {

  @Inject
  private SecureDatastoreImpl datastore;
  @Inject
  private Excel excel;

  public Response get(final String customerName, final String departmentName, final long productId) throws IOException {
    final Key<Customer> customer = Key.create(Customer.class, customerName);
    final Key<Department> department = Key.create(customer, Department.class, departmentName);
    final Key<Product> key = Key.create(department, Product.class, productId);
    final Product product = datastore.loadUnauthorized(key).now();
    final HSSFWorkbook workbook = excel.createBookWithHeaders("Product", 5000, "RowNumber", "Name", "Category", "Description", "Cost");
    excel.addRow(workbook, 0, 1, 1, product.getName(), product.getCategory().name(), product.getDescription().getValue(), Optional.ofNullable(product.getCost()).orElse(0.0d));
    return excel.toResponse(workbook, product.getName() + ".xls");
  }
}
