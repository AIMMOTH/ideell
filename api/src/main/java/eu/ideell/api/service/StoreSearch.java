package eu.ideell.api.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.collect.Maps;

import eu.ideell.api.mongodb.SecureMongoDbImpl;
import eu.ideell.api.mongodb.entity.Product;
import eu.ideell.api.service.model.ProductResource;
import eu.ideell.api.service.model.StoreSearchRequest;
import se.cewebab.stockholm.search.SearchDocument;
import se.cewebab.stockholm.search.SearchService;
import se.cewebab.stockholm.util.Monad;

public class StoreSearch {

  @Autowired
  private SearchService search;
  @Autowired
  private SecureMongoDbImpl datastore;

  public List<ProductResource> create(final StoreSearchRequest request) throws Exception {
    final HashMap<String, String> facets = Maps.newHashMap();
    facets.put("version", "v2");

    return Monad.monad(search.query("products", facets, Maps.newHashMap(), Maps.newHashMap(), Maps.newHashMap(), 1000))
      .map(collection -> collection.stream())
      .map(documents -> documents.map(document -> {
        final long id = (long) document.getFields().get(SearchDocument.idFieldName);
        return datastore.loadUnauthorized(Product.class, id);
      }))
      .map(stream -> stream.map(ProductResource::new))
      .map(stream -> stream.collect(Collectors.toList()))
      .get()
      ;
  }

}
