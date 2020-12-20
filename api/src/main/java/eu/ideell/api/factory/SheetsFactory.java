package eu.ideell.api.factory;

import java.net.URISyntaxException;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import se.cewebab.stockholm.sheets.Sheets;
import se.cewebab.stockholm.sheets.SheetsDevelopmentImpl;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

public class SheetsFactory implements Factory<Sheets> {

  @Inject
  private Log log;
  @Inject
  private Settings settings;

  @Override
  public Sheets provide() {
    try {
      return new SheetsDevelopmentImpl(settings.getText("Sheets", "Email"), settings.getText("Sheets", "Service_Account_Key"));
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
