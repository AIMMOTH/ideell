package eu.euphoros.api.service;

import java.io.IOException;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.common.collect.Lists;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;

import eu.euphoros.api.datastore.SecureDatastoreImpl;
import eu.euphoros.api.datastore.entity.CustomerParent;
import eu.euphoros.api.datastore.entity.DepartmentParent;
import eu.euphoros.api.datastore.entity.User;
import eu.euphoros.api.datastore.entity.UserParent;
import eu.euphoros.api.jaxrs.UserPrincipal;
import eu.euphoros.api.service.model.BigQueryProduct;
import eu.euphoros.api.service.model.Product;
import eu.euphoros.api.service.model.ProductRequest;
import eu.euphoros.api.service.model.ProductResource;
import se.cewebab.stockholm.appengine.DocumentBuilder;
import se.cewebab.stockholm.appengine.Search;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.email.Email;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

public class Products {

  @Inject
  private Log log;
  @Inject
  private Settings settings;
  @Inject
  private SecureDatastoreImpl datastore;
  @Inject
  private Search search;
  @Inject
  private BigQueryIntegration bigQuery;
  @Inject
  private Email email;

  public Optional<ProductResource> get(final String customerName, final String departmentName, final long productId) {
    final Key<CustomerParent> customer = Key.create(CustomerParent.class, customerName);
    final Key<DepartmentParent> department = Key.create(customer, DepartmentParent.class, departmentName);
    final Key<Product> key = Key.create(department, Product.class, productId);

    return Optional.of(datastore.loadUnauthorized(key))
        .map(LoadResult::now)
        .filter(Objects::nonNull)
        .map(ProductResource::new)
        ;
  }

  public long create(final UserPrincipal principal, final ProductRequest model) {
    final User user = principal.getUser();
    final Key<UserParent> parent = user.getUserParent();
    final Key<DepartmentParent> department = parent.getParent();
    final Key<CustomerParent> customer = department.getParent();

    final Product entity = new Product(user, model);
    final long id = datastore.saveEntityAuthorized(user, entity).now().getId();
    final BigQueryProduct bigQueryProduct = new BigQueryProduct(principal.getUser(), entity);

    final DocumentBuilder document = new DocumentBuilder(id, Locale.FRANCE)
      .facet("version", "v2")
      .facetAndField("customer", customer.getName())
      .facetAndField("department", department.getName())
      .facetAndField("user", parent.getName())

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
