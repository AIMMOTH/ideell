package eu.ideell.api.jersey;

import java.nio.file.attribute.UserPrincipal;

import org.glassfish.hk2.utilities.binding.AbstractBinder;

import eu.ideell.api.datastore.SecureDatastoreImpl;
import eu.ideell.api.factory.UserPrincipalFactory;
import eu.ideell.api.service.Products;
import eu.ideell.api.service.StoreSearch;
import se.cewebab.stockholm.appengine.Image;
import se.cewebab.stockholm.appengine.ImageImpl;
import se.cewebab.stockholm.appengine.Search;
import se.cewebab.stockholm.appengine.SearchImpl;
import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.bigquery.BigQueryIntegration;
import se.cewebab.stockholm.jersey.JerseyDefaultConfig;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Settings;

public class TestJerseyDefaultConfig extends JerseyDefaultConfig {

  public TestJerseyDefaultConfig() {
    super();
    register(new AbstractBinder() {

      @Override
      protected void configure() {
        // Log
        bindAsContract(Log.class);
        // Settings
        bindFactory(TestSettingsFactory.class).to(Settings.class);
        // Other
        bindFactory(UserPrincipalFactory.class).to(UserPrincipal.class);
        bindAsContract(SystemProperties.class);
        bind(TestAuth0.class).to(Auth0.class);
        bindAsContract(SecureDatastoreImpl.class);
        bind(TestBigQueryImpl.class).to(BigQueryIntegration.class);

        bind(ImageImpl.class).to(Image.class);
        bind(SearchImpl.class).to(Search.class);
        bind(Products.class).to(Products.class);
        bind(StoreSearch.class).to(StoreSearch.class);
      }
    });
  }

  @Override
  protected boolean useRoles() {
    return true;
  }

  @Override
  protected boolean sendBeanValidationErrorInResponse() {
    return true;
  }

  @Override
  protected boolean useWadlFeature() {
    return false;
  }

  @Override
  protected String[] getJaxRsPackages() {
    return new String[] {"se.ceweb.stockholm.jaxrs"};
  }

  @Override
  protected String getName() {
    return "TEST JERSEY";
  }
}
