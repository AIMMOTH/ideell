package eu.euphoros.api.jersey;

import java.util.UUID;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.apache.http.HttpHeaders;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.After;
import org.junit.Before;

import com.google.appengine.api.datastore.dev.LocalDatastoreService.AutoIdAllocationPolicy;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalSearchServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Deleter;
import com.googlecode.objectify.cmd.Saver;

import eu.euphoros.api.servlet.ServletConfig;
import se.cewebab.stockholm.util.Monad;

public class TestJersey extends JerseyTest {

  private static final LocalDatastoreServiceTestConfig datastore = new LocalDatastoreServiceTestConfig().setAutoIdAllocationPolicy(AutoIdAllocationPolicy.SEQUENTIAL).setStoreDelayMs(10);
  private static final LocalSearchServiceTestConfig search = new LocalSearchServiceTestConfig();
  private static final LocalServiceTestHelper helper = new LocalServiceTestHelper(datastore, search);

  @Override
  protected Application configure() {
    System.out.println("Store delay:" + datastore.getStoreDelayMs());
    return new TestJerseyDefaultConfig();
  }

  @Override
  @Before
  public void setUp() throws Exception {
    helper.setUp();
    ServletConfig.register();
    ObjectifyService.begin();
    super.setUp();
  }

  @Override
  @After
  public void tearDown() throws Exception {
    helper.tearDown();
    super.tearDown();
  }

  protected <Request, Response> Response postUnauthenticated(final String path, final Request body, final Class<Response> resource) {
    return Monad.monad(Entity.<Request>entity(body, MediaType.APPLICATION_JSON))
        .map(entity -> target(path).request().post(entity, resource))
        .get()
        ;
  }

  protected <Request, Response> Response postAuthenticated(final String path, final Request body, final Class<Response> resource) {
    return Monad.monad(Entity.<Request>entity(body, MediaType.APPLICATION_JSON))
        .map(entity -> target(path).request()
            .header(HttpHeaders.AUTHORIZATION, TestAuth0.authorizedHeader)
            .header("X-Appengine-Country", "Sweden")
            .header("X-Appengine-Region", "Stockholm")
            .header("X-Appengine-City", "Stockholm")
            .header("X-Cloud-Trace-Context", UUID.randomUUID().toString())
            .post(entity, resource))
        .get()
        ;
  }

  protected Saver testSave() {
    return ObjectifyService.ofy().save();
  }

  protected <T> T testLoad(final Key<T> key) {
    return ObjectifyService.ofy().load().key(key).now();
  }

  protected <T> T testLoad(final Class<T> klass, final String name) {
    return ObjectifyService.ofy().load().key(Key.create(klass, name)).now();
  }

  protected <T> T testLoad(final Class<T> klass, final long id) {
    return ObjectifyService.ofy().load().key(Key.create(klass, id)).now();
  }

  protected Deleter testDelete() {
    return ObjectifyService.ofy().delete();
  }
}
