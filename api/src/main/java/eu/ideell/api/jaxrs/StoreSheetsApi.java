package eu.ideell.api.jaxrs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import eu.ideell.api.service.StoreSheets;
import eu.ideell.api.service.model.SheetsResponse;

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
