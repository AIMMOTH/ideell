package eu.ideell.api.jaxrs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.service.StoreSearch;
import eu.ideell.api.service.model.StoreSearchRequest;
import eu.ideell.api.service.model.StoreSearchResponse;

@Controller("public/search")
public class StoreSearchApi {

  @Autowired
  private StoreSearch service;

  @PostMapping
  public StoreSearchResponse post(final StoreSearchRequest request) throws Exception {
    return new StoreSearchResponse(service.create(request));
  }
}
