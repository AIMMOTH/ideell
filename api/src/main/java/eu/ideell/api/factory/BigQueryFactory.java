package eu.ideell.api.factory;

import org.springframework.beans.factory.annotation.Autowired;

import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.bigquery.BigQueryImpl;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.util.Settings;

public class BigQueryFactory implements Factory<BigQueryIntegration> {

  @Autowired
  private SystemProperties system;
  @Autowired
  private Settings settings;

  @Override
  public BigQueryIntegration provide() {
    return new BigQueryImpl(settings.getText("BigQuery", "Service_Account_Key"));
  }

  @Override
  public void dispose(final BigQueryIntegration instance) {

  }

}
