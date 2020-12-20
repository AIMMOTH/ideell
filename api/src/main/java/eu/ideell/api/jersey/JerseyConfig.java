package eu.ideell.api.jersey;

import java.io.IOException;

import javax.ws.rs.ApplicationPath;

import se.cewebab.stockholm.jersey.JerseyDefaultConfig;

@ApplicationPath("/api/v1/")
public class JerseyConfig extends JerseyDefaultConfig {

  public static final String[] jaxRsPackages = new String[] {
      "eu.ideell.api.jaxrs"
      };

  public JerseyConfig() throws IOException {
    super();
    register(new InjectionBinder());
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
    return jaxRsPackages;
  }

  @Override
  protected String getName() {
    return "Euphoros - Ideell social media API";
  }
}
