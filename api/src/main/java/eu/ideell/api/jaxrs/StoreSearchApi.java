package eu.ideell.api.jaxrs;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import eu.ideell.api.service.StoreSearch;
import eu.ideell.api.service.model.StoreSearchRequest;
import eu.ideell.api.service.model.StoreSearchResponse;

@Path("public/search")
public class StoreSearchApi {

  @Inject
  private StoreSearch service;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  public StoreSearchResponse post(final StoreSearchRequest request) {
    return new StoreSearchResponse(service.create(request));
  }
}