package eu.euphoros.api.jaxrs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import eu.euphoros.api.service.StoreSheets;
import eu.euphoros.api.service.model.SheetsResponse;

@Path("sheets")
@RolesAllowed("user")
public class StoreSheetsApi {

  @Inject
  private StoreSheets sheets;

  @GET
  public SheetsResponse get() throws GeneralSecurityException, IOException, URISyntaxException, ParseException {
    return sheets.get();
  }
}
