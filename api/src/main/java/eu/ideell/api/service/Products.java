package eu.ideell.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import eu.ideell.api.mongodb.SecureMongoDbImpl;
import eu.ideell.api.mongodb.entity.Product;
import eu.ideell.api.mongodb.entity.User;
import eu.ideell.api.service.model.BigQueryProduct;
import eu.ideell.api.service.model.ProductRequest;
import eu.ideell.api.service.model.ProductResource;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.email.Email;
import se.cewebab.stockholm.search.SearchDocument;
import se.cewebab.stockholm.util.Locales;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

public class Products {

  @Autowired
  private Log log;
  @Autowired
  private Settings settings;
  @Autowired
  private SecureMongoDbImpl datastore;
  @Autowired
  private se.cewebab.stockholm.search.SearchService search;
  @Autowired
  private BigQueryIntegration bigQuery;
  @Autowired
  private Email email;

  public Optional<ProductResource> get(final String customerName, final String departmentName, final long productId) {
    return Optional.of(datastore.loadUnauthorized(Product.class, productId))
        .filter(Objects::nonNull)
        .map(ProductResource::new)
        ;
  }

  public long create(final User user, final ProductRequest model) throws Exception {
    final String department = user.getDepartmentName();
    final String customer = user.getCustomerName();

    final Product entity = new Product(user, model);
    final long id = datastore.saveAuthorized(user, entity).getProductId();
    final BigQueryProduct bigQueryProduct = new BigQueryProduct(user, entity);

    final SearchDocument document = new SearchDocument(id, Locales.swedish)
      .addFacet("version", "v2")
      .addFacet("customer", customer)
      .addFacet("department", department)
      .addFacet("user", user.getUserName())

      .addDate("documentUpdated", new Date())
      .addText("name", model.getName())
      .addText("description", model.getDescription())
      .addDouble("cost", Optional.ofNullable(model.getCost()).orElse(0.0d))
      .addAtom("category", model.getCategory().name())
      ;
    try {
      search.putOnIndex("products", document);
//      // BigQuery
//      final String dataset = settings.getText("BigQuery", "Dataset");
//      final String table = settings.getText("BigQuery", "Products_Table");
//      log.log("Inserting into dataset " + dataset + " table " + table);
//      bigQuery.insertAll(dataset, table, Lists.newArrayList(bigQueryProduct));
      // Send email
      email.send("info@cewebab.se", "info@cewebab.se", "Ny produkt!", "d-d0171a6381474155b5262941cc7c9f95", model);
      return id;
    }
    catch (final IOException e) {
      log.warning("Deleting created entity");
      datastore.deleteAsSystem(entity);
      log.warning(e);
      throw new Exception("internal server error");
    }
  }

}
