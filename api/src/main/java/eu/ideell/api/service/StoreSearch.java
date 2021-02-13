package eu.ideell.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import com.google.appengine.api.search.Index;
import com.google.appengine.api.search.QueryOptions;
import com.google.common.collect.Maps;
import com.googlecode.objectify.Key;

import eu.ideell.api.datastore.SecureDatastoreImpl;
import eu.ideell.api.datastore.entity.Customer;
import eu.ideell.api.datastore.entity.Department;
import eu.ideell.api.service.model.Product;
import eu.ideell.api.service.model.ProductResource;
import eu.ideell.api.service.model.StoreSearchRequest;
import se.cewebab.stockholm.appengine.SearchService;
import se.cewebab.stockholm.util.Monad;

public class StoreSearch {

  @Inject
  private SearchService search;
  @Inject
  private SecureDatastoreImpl datastore;

  public List<ProductResource> create(final StoreSearchRequest request) {
    final Index index = search.getIndex("products");
    final HashMap<String, String> facets = Maps.newHashMap();
    facets.put("version", "v2");

    return Monad.monad(QueryOptions.newBuilder().setLimit(10).build())
      .map(options -> search.getResults(index, options, request.getQuery(), facets))
      .map(result -> result.getResults())
      .map(collection -> collection.stream())
      .map(documents -> documents.map(document -> {
        final String customerName = document.getFields("customer").iterator().next().getText();
        final String departmentName = document.getFields("department").iterator().next().getText();

        final Key<Customer> customer = Key.create(Customer.class, customerName);
        final Key<Department> department = Key.create(customer, Department.class, departmentName);

        return Key.create(department, Product.class, Long.valueOf(document.getId()));
      }))
      .map(stream -> stream.collect(Collectors.toList()))
      .map(keys -> datastore.loadUnauthorized(keys))
      .map(map -> map.values().stream())
      .map(stream -> stream.map(ProductResource::new))
      .map(stream -> stream.collect(Collectors.toList()))
      .get()
      ;
  }

}
