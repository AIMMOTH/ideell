package eu.ideell.api.jaxrs;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.ext.Provider;

import se.cewebab.stockholm.jaxrs.SetHeadersResponse;
import se.cewebab.stockholm.util.Settings;

@Provider
public class ApiResponse extends SetHeadersResponse {

  @Inject
  private Settings settings;

  @Override
  public void filter(final ContainerRequestContext request, final ContainerResponseContext response) throws IOException {
    super.filter(request, response);
  }

  @Override
  protected boolean implementCsp() {
    return false;
  }

  @Override
  protected String getCspDomains() {
    return settings.getSettingText("CSP");
  }

  @Override
  protected boolean checkOrigin(final String origin) {
    return settings.getSettingList("CORS").contains(origin);
  }

}
