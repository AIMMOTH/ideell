package eu.ideell.api.service;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table.TableBuilder;

import com.googlecode.objectify.Key;

import eu.ideell.api.datastore.SecureDatastoreImpl;
import eu.ideell.api.datastore.entity.CustomerParent;
import eu.ideell.api.datastore.entity.DepartmentParent;
import eu.ideell.api.service.model.Product;
import se.cewebab.stockholm.pdf.PdfDocument;

public class ProductPdf {

  @Inject
  private SecureDatastoreImpl datastore;
  @Inject
  private PdfDocument pdf;

  public Response get(final String customerName, final String departmentName, final long productId) throws IOException {
    final Key<CustomerParent> customer = Key.create(CustomerParent.class, customerName);
    final Key<DepartmentParent> department = Key.create(customer, DepartmentParent.class, departmentName);
    final Key<Product> key = Key.create(department, Product.class, productId);
    final Product product = datastore.loadUnauthorized(key).now();
    final PDDocument document = new PDDocument();
    final TableBuilder table = pdf.createBuilder(10.0f, new float[] {100.0f, 200.0f, 100.0f});
    table.addRow(Row.builder()
        .add(pdf.cell(product.getName(), 12).build())
        .add(pdf.cell(product.getCategory().name(), 12).build())
        .add(pdf.cell(product.getDescription().getValue(), 12).build())
        .build());

    pdf.addPageAndTable(document, pdf.getA4LandscapeSupplier().get(), table.build(), () -> pdf.getA4LandscapeSupplier().get());
    return pdf.toResponse(document);
  }

}
