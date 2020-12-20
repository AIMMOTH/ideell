package eu.ideell.api.jersey;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.auth0.Auth0Impl;
import se.cewebab.stockholm.util.Settings;

public class Auth0Factory implements Factory<Auth0> {

  @Inject
  private Settings settings;

  @Override
  public Auth0 provide() {
    return new Auth0Impl(settings.getText("Auth0", "Issuer"), settings.getText("Auth0", "JWK_Provider"));
  }

  @Override
  public void dispose(final Auth0 instance) {

  }

}
