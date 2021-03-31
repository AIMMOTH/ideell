package eu.ideell.api.mongodb;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import eu.ideell.api.mongodb.entity.Admin;
import eu.ideell.api.mongodb.entity.Customer;
import eu.ideell.api.mongodb.entity.Department;
import eu.ideell.api.mongodb.entity.User;
import eu.ideell.api.spring.UnauthorizedException;
import lombok.NoArgsConstructor;
import se.cewebab.stockholm.auth0.Auth0;
import se.cewebab.stockholm.mongodb.SecureEntity;
import se.cewebab.stockholm.mongodb.SecureMongoDb;
import se.cewebab.stockholm.util.Either;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Monad;
import se.cewebab.stockholm.util.Settings;

@NoArgsConstructor
public class SecureMongoDbImpl extends AbstractSecureMongoDb
    implements SecureMongoDb<Admin, User, Customer, Department> {

  private static final Gson gson = new Gson();

  @Autowired
  private Log log;
  @Autowired
  private Settings settings;
  @Autowired
  private Auth0 auth0;

  @Override
  public Admin loadAdminOrCreate(final DecodedJWT decodedJwt) {
    return loadAdminOrCreate(decodedJwt.getSubject(), () -> getUserInfo(decodedJwt.getToken()),
        () -> settings.getSettingObject("System").get("First_Email").getAsString());
  }

  @Override
  public User loadUserOrCreate(final DecodedJWT decodedJwt, final String customerName, final String departmentName) {
    return loadUserOrCreate(decodedJwt.getSubject(), () -> getUserInfo(decodedJwt.getToken()), customerName,
        departmentName);
  }

  private Auth0UserInfo getUserInfo(final String token) {
    final Either<Exception, JsonObject> userInfo = auth0.getUserInfo(token);
    if (userInfo.isRight()) {
      return Monad.monad(userInfo.getRight()).map(value -> gson.fromJson(value, Auth0UserInfo.class)).get();
    } else {
      log.warning(userInfo.getLeft());
      return new Auth0UserInfo();
    }
  }

  @Override
  public Customer loadCustomer(final User user) {
    return loadCustomer(user);
  }

  @Override
  public Department loadDepartment(final User user) {
    return loadDepartment(user);
  }

  @Override
  public <Entity> Entity loadUnauthorized(final Class<Entity> klass, final Object id) {
    if (getPublicKinds().contains(klass.getSimpleName())) {
      return load(klass, id);
    } else {
      throw new RuntimeException("Not public.");
    }
  }

//  @Override
//  public <Entity> Map<Key<Entity>, Entity> loadUnauthorized(final List<Key<Entity>> keys) {
//    if (keys.stream().anyMatch(key -> !getPublicKinds().contains(key.getKind()))) {
//      throw new BadRequestException(Response.status(Status.BAD_REQUEST).build());
//    }
//    return load().keys(keys);
//  }

  @Override
  public <Entity extends SecureEntity, Id extends Object> Entity loadAuthorized(final User user, final Class<Entity> klass, final Id id) {
    final Entity entity = load(klass, id);
    if (Objects.equals(user.getCustomerName(), entity.getCustomerName()) && Objects.equals(user.getDepartmentName(), entity.getDepartmentName())) {
      return entity;
    } else {
      throw new RuntimeException("");
    }
  }

  @Override
  public List<String> getPublicKinds() {
    return Lists.newArrayList("Product");
  }

  public <Entity> void deleteAsSystem(final Entity entity) {
    log.warning(String.format("Deleting entity as system %s", String.valueOf(entity)));
    delete(entity);
  }

//  public <Entity> Result<Key<Entity>> saveEntityAsSystem(final Entity entity) {
//    return save().entity(entity);
//  }

//  @Override
//  public <Entity> Map<Key<Entity>, Entity> loadAuthorized(final User user, final List<Key<Entity>> keys) {
//    return null;
//  }

//  @Override
//  public <E extends SecureEntity<Department>> Result<Key<E>> saveDepartmentEntityAuthorized(final User user, final E entity) {
//    return null;
//  }
  public <E extends SecureEntity> E saveAuthorized(final User user, final E entity) {
    if (Objects.equals(user.getCustomerName(), entity.getCustomerName()) && Objects.equals(user.getDepartmentName(), entity.getDepartmentName())) {
      return save(entity);
    } else {
      throw new UnauthorizedException();
    }
  }

//  @Override
//  public void transactionWithMax25EntityGroups(final VoidWork work, final int retries) {
//  }
//
//  @Override
//  public <R> R transactionWithMax25EntityGroups(final Work<R> work, final int retries) {
//    return null;
//  }
//
//  @Override
//  public <T> T transactionWithMax25EntityGroups(final Supplier<T> transaction) {
//    return null;
//  }
//
//  @Override
//  public void updateEntityAuthorized(final User user, final SecureEntity<Department> entity) {
//  }
}
