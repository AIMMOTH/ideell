package eu.ideell.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.ws.rs.InternalServerErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Lists;

import eu.ideell.api.jaxrs.UserPrincipal;
import eu.ideell.api.mongodb.SecureDatastoreImpl;
import eu.ideell.api.mongodb.entity.Product;
import eu.ideell.api.mongodb.entity.User;
import eu.ideell.api.service.model.BigQueryProduct;
import eu.ideell.api.service.model.ProductRequest;
import eu.ideell.api.service.model.ProductResource;
import se.cewebab.stockholm.appengine.DocumentBuilder;
import se.cewebab.stockholm.appengine.SearchService;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.email.Email;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

public class Products {

  @Autowired
  private Log log;
  @Autowired
  private Settings settings;
  @Autowired
  private SecureDatastoreImpl datastore;
  @Autowired
  private SearchService search;
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

  public long create(final UserPrincipal principal, final ProductRequest model) {
    final User user = principal.getUser();
    final String department = user.getDepartmentName();
    final String customer = user.getCustomerName();

    final Product entity = new Product(user, model);
    final long id = datastore.saveDepartmentEntityAuthorized(user, entity).getProductId();
    final BigQueryProduct bigQueryProduct = new BigQueryProduct(principal.getUser(), entity);

    final DocumentBuilder document = new DocumentBuilder(id, Locale.FRANCE)
      .facet("version", "v2")
      .facetAndField("customer", customer)
      .facetAndField("department", department)
      .facetAndField("user", user.getUserName())

      .field("documentUpdated", new Date())
      .field("name", model.getName())
      .field("description", model.getDescription())
      .field("cost", Optional.ofNullable(model.getCost()).orElse(0.0d))
      .field("category", model.getCategory().name())
      ;
    try {
      search.putOnIndex(search.getIndex("products"), document);
      // BigQuery
      final String dataset = settings.getText("BigQuery", "Dataset");
      final String table = settings.getText("BigQuery", "Products_Table");
      log.log("Inserting into dataset " + dataset + " table " + table);
      bigQuery.insertAll(dataset, table, Lists.newArrayList(bigQueryProduct));
      // Send email
      email.send("info@cewebab.se", "info@cewebab.se", "Ny produkt!", "d-d0171a6381474155b5262941cc7c9f95", model);
      return id;
    }
    catch (IOException | InterruptedException e) {
      log.warning("Deleting created entity");
      final Key<Product> key = Key.create(principal.getUser().getUserParent().getParent(), Product.class, id);
      datastore.deleteAsSystem(key);
      log.warning(e);
      throw new InternalServerErrorException(Response.status(Status.INTERNAL_SERVER_ERROR).build());
    }
  }

}
