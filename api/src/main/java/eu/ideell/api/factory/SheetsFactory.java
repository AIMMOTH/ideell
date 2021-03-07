package eu.ideell.api.factory;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;

import se.cewebab.stockholm.sheets.Sheets;
import se.cewebab.stockholm.sheets.SheetsImpl;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

public class SheetsFactory implements Factory<Sheets> {

  @Autowired
  private Log log;
  @Autowired
  private Settings settings;

  @Override
  public Sheets provide() {
    try {
      return new SheetsImpl(settings.getText("Sheets", "Email"), settings.getText("Sheets", "Service_Account_Key"));
    }
    catch (final URISyntaxException e) {
      log.warning(e);
      return null;
    }
  }

  @Override
  public void dispose(final Sheets instance) {
  }

}
