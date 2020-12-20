package eu.ideell.api.datastore;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.Result;
import com.googlecode.objectify.Work;

import eu.ideell.api.datastore.entity.Admin;
import eu.ideell.api.datastore.entity.Customer;
import eu.ideell.api.datastore.entity.Department;
import eu.ideell.api.datastore.entity.DepartmentParent;
import eu.ideell.api.datastore.entity.User;
import lombok.NoArgsConstructor;
import se.cewebab.stockholm.appengine.SecureDatastore;
import se.cewebab.stockholm.appengine.SecureEntity;
import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.util.Either;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Monad;
import se.cewebab.stockholm.util.Settings;

@NoArgsConstructor
public class SecureDatastoreImpl extends AbstractSecureDatastore implements SecureDatastore<Admin, User, Customer, Department, DepartmentParent> {

  private static final Gson gson = new Gson();

  @Inject
  private Log log;
  @Inject
  private Settings settings;
  @Inject
  private Auth0 auth0;

  @Override
  public Admin loadAdminOrCreate(final DecodedJWT decodedJwt) throws NotFoundException {
    return loadAdminOrCreate(decodedJwt.getSubject(), () -> getUserInfo(decodedJwt.getToken()), () -> settings.getSettingObject("System").get("First_Email").getAsString());
  }

  @Override
  public User loadUserOrCreate(final DecodedJWT decodedJwt, final String customerName, final String departmentName) throws NotFoundException {
    return loadUserOrCreate(decodedJwt.getSubject(), () -> getUserInfo(decodedJwt.getToken()), customerName, departmentName);
  }

  private Auth0UserInfo getUserInfo(final String token) {
    final Either<Exception, JsonObject> userInfo = auth0.getUserInfo(token);
    if (userInfo.isRight()) {
      return Monad.monad(userInfo.getRight())
          .map(value -> gson.fromJson(value, Auth0UserInfo.class))
          .get()
          ;
    } else {
      log.warning(userInfo.getLeft());
      return new Auth0UserInfo();
    }
  }

  @Override
  public LoadResult<Customer> loadCustomer(final User user) {
    return Monad.monad(user.getUserParent())
        .map(userParent -> userParent.getParent())
        .map(departmentParent -> departmentParent.getParent())
        .map(customerParent -> load().key(Key.create(Customer.class, customerParent.getName())))
        .get()
        ;
  }

  @Override
  public LoadResult<Department> loadDepartment(final User user) {
    return Monad.monad(user.getUserParent())
        .map(userParent -> userParent.getParent())
        .map(departmentParent -> load().key(Key.create(Department.class, departmentParent.getName())))
        .get()
        ;
  }

  @Override
  public <Entity> LoadResult<Entity> loadUnauthorized(final Key<Entity> key) {
    if (getPublicKinds().contains(key.getKind())) {
      return load().key(key);
    } else {
      throw new BadRequestException(Response.status(Status.BAD_REQUEST).build());
    }
  }

  @Override
  public <Entity> Map<Key<Entity>, Entity> loadUnauthorized(final List<Key<Entity>> keys) {
    if (keys.stream().anyMatch(key -> !getPublicKinds().contains(key.getKind()))) {
      throw new BadRequestException(Response.status(Status.BAD_REQUEST).build());
    }
    return load().keys(keys);
  }

  @Override
  public <Entity> LoadResult<Entity> loadAuthorized(final User user, final Class<Entity> klass, final long id) {
    final Key<DepartmentParent> parent = user.getUserParent().getParent();
    return load().key(Key.create(parent, klass, id));
  }

  @Override
  public <E extends SecureEntity<DepartmentParent>> Result<Key<E>> saveEntityAuthorized(final User user, final E entity) {
    entity.setParent(user.getUserParent().getParent());
    return save().entity(entity);
  }

  private <R> R transactionWithMax25EntityGroups(final Work<R> work, final int retries) {
    return ObjectifyService.ofy().transactNew(retries, work);
  }

  @Override
  public List<String> getPublicKinds() {
    return Lists.newArrayList("Product");
  }

  public void deleteAsSystem(final Key<? extends Object> key) {
    log.warning("Deleting entity as system " + key);
    delete().key(key);
  }

  public <Entity> Result<Key<Entity>> saveEntityAsSystem(final Entity entity) {
    return save().entity(entity);
  }
}
