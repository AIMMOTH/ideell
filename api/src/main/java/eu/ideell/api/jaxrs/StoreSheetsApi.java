package eu.ideell.api.jaxrs;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;

import javax.annotation.security.RolesAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import eu.ideell.api.service.StoreSheets;
import eu.ideell.api.service.model.SheetsResponse;

@Controller("sheets")
@RolesAllowed("user")
public class StoreSheetsApi {

  @Autowired
  private StoreSheets sheets;

  @GetMapping
  public SheetsResponse get() throws GeneralSecurityException, IOException, URISyntaxException, ParseException {
    return sheets.get();
  }
}
