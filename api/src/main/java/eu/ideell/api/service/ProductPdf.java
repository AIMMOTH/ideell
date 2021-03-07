package eu.ideell.api.service;

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.vandeseer.easytable.structure.Row;
import org.vandeseer.easytable.structure.Table.TableBuilder;

import eu.ideell.api.mongodb.SecureMongoDbImpl;
import eu.ideell.api.mongodb.entity.Product;
import se.cewebab.stockholm.pdf.PdfDocument;

public class ProductPdf {

  @Autowired
  private SecureMongoDbImpl datastore;
  @Autowired
  private PdfDocument pdf;

  public byte[] get(final String customerName, final String departmentName, final long productId) throws IOException {
    final Product product = datastore.loadUnauthorized(Product.class, productId);
    final PDDocument document = new PDDocument();
    final TableBuilder table = pdf.createBuilder(10.0f, new float[] {100.0f, 200.0f, 100.0f});
    table.addRow(Row.builder()
        .add(pdf.cell(product.getName(), 12).build())
        .add(pdf.cell(product.getCategory().name(), 12).build())
        .add(pdf.cell(product.getDescription(), 12).build())
        .build());

    pdf.addPageAndTable(document, pdf.getA4LandscapeSupplier().get(), table.build(), () -> pdf.getA4LandscapeSupplier().get());
    return pdf.toResponse(document);
  }

}
