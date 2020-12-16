package eu.euphoros.api.jaxrs;

import java.io.IOException;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.auth0.jwk.JwkException;

import eu.euphoros.api.datastore.SecureDatastoreImpl;
import se.cewebab.stockholm.appengine.SystemProperties;
import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.jaxrs.AuthorizeRequest;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Monad;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiRequest extends AuthorizeRequest {

  @Inject
  private Log log;
  @Inject
  private Auth0 auth0;
  @Inject
  private SecureDatastoreImpl datastore;
  @Inject
  private SystemProperties system;

  @Override
  public void filter(final ContainerRequestContext request) throws IOException {
    validateXAppEngineHeaders(request.getHeaders());
    try {
      auth0.authorize(request)
        .ifPresent(jwt -> {
          Monad.monad(datastore.loadUserOrCreate(jwt, "default", "default"))
            .accept(user -> {
              log.log("User logged in " + user.getUserName());
              request.setSecurityContext(new Authorized(user));
            })
            ;
        });
    }
    catch (final JwkException e) {
      e.printStackTrace();
    }
  }

  private void validateXAppEngineHeaders(final MultivaluedMap<String, String> headers) {
    if (system.isProduction() &&
        (!headers.containsKey("X-Appengine-Country")
        || !headers.containsKey("X-Appengine-Region")
        || !headers.containsKey("X-Appengine-City")
        || !headers.containsKey("X-Cloud-Trace-Context"))) {

      throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED));
    }
  }

}
