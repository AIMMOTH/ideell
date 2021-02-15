package eu.ideell.api.factory;

import org.springframework.beans.factory.annotation.Autowired;

import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.bigquery.BigQueryDevelopmentImpl;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.bigquery.BigQueryProductionImpl;
import se.cewebab.stockholm.util.Settings;

public class BigQueryFactory implements Factory<BigQueryIntegration> {

  @Autowired
  private SystemProperties system;
  @Autowired
  private Settings settings;

  @Override
  public BigQueryIntegration provide() {
    if (system.isProduction()) {
      return new BigQueryProductionImpl();
    }
    return new BigQueryDevelopmentImpl(settings.getText("BigQuery", "Service_Account_Key"));
  }

  @Override
  public void dispose(final BigQueryIntegration instance) {

  }

}
