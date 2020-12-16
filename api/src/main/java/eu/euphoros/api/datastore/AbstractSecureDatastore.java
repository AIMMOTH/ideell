package eu.euphoros.api.datastore;

import java.util.Objects;
import java.util.function.Supplier;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Deleter;
import com.googlecode.objectify.cmd.Loader;
import com.googlecode.objectify.cmd.Saver;

import eu.euphoros.api.datastore.entity.Admin;
import eu.euphoros.api.datastore.entity.AdminInvitation;
import eu.euphoros.api.datastore.entity.AdminParent;
import eu.euphoros.api.datastore.entity.Customer;
import eu.euphoros.api.datastore.entity.CustomerParent;
import eu.euphoros.api.datastore.entity.Department;
import eu.euphoros.api.datastore.entity.DepartmentParent;
import eu.euphoros.api.datastore.entity.User;
import eu.euphoros.api.datastore.entity.UserInvitation;
import eu.euphoros.api.datastore.entity.UserParent;
import se.cewebab.stockholm.util.Log;
import se.cewebab.stockholm.util.Monad;

public abstract class AbstractSecureDatastore {

  private static final Log log = new Log("AbstractSecureDatastore");

  Admin loadAdminOrCreate(final String subject, final Supplier<Auth0UserInfo> getUserInfo, final Supplier<String> getFirstEmail) throws NotFoundException {
    return Monad.monad(Key.create(AdminParent.class, subject))
        .map(parentKey -> Key.create(parentKey, Admin.class, subject))
        .map(key -> load().key(key).now())
        .map(entity -> {
          if (Objects.isNull(entity)) {
            final Auth0UserInfo userInfo = getUserInfo.get();
            final AdminInvitation invitation = load().key(Key.create(AdminInvitation.class, userInfo.getEmail())).now();
            if (Objects.nonNull(invitation) || userInfo.getEmail().equals(getFirstEmail.get())) {
              log.log("Creating ADMIN with subject " + userInfo.getSub());
              return Monad.monad(new AdminParent(userInfo.getSub()))
                  .map(parent -> save().entity(parent).now())
                  .map(key -> {
                    final Admin admin = new Admin(key, userInfo);
                    save().entity(admin).now();
                    return admin;
                  })
                  .get()
                  ;
            } else {
              throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED));
            }
          } else {
            return entity;
          }
        })
        .get()
        ;
  }

  User loadUserOrCreate(final String subject, final Supplier<Auth0UserInfo> getUserInfo, final String customerName, final String departmentName) throws NotFoundException {
    final Key<CustomerParent> customer = loadCustomerOrCreate(customerName);
    final Key<DepartmentParent> department = loadDepartmentOrCreate(customer, departmentName);
    return Monad.monad(createUserKey(department, subject))
        .map(key -> load().key(key).now())
        .map(entity -> {
          if (Objects.isNull(entity)) {
            final Auth0UserInfo userInfo = getUserInfo.get();
            final Customer customerSettings = load().key(Key.create(customer, Customer.class, customer.getName())).now();
            if (customerSettings.isInvitationMandatoryForNewUsers()) {
              final UserInvitation invitation = load().key(Key.create(UserInvitation.class, userInfo.getEmail())).now();
              if (Objects.isNull(invitation)) {
                throw new NotAuthorizedException(Response.status(Status.UNAUTHORIZED));
              }
            }
            log.log("Creating user with subject " + userInfo.getSub());
            return Monad.monad(new UserParent(department, userInfo.getSub()))
                .map(parent -> save().entity(parent).now())
                .map(key -> {
                  final User user = new User(key, userInfo);
                  save().entity(user).now();
                  return user;
                })
                .get()
                ;
          } else {
            return entity;
          }
        })
        .get()
        ;
  }

  Key<CustomerParent> loadCustomerOrCreate(final String customerName) {
    final Key<CustomerParent> key = Key.create(CustomerParent.class, customerName);
    if (Objects.nonNull(load().key(key).now())) {
      return key;
    } else {
      final CustomerParent parent = new CustomerParent(customerName);
      final Customer customer = new Customer(customerName);
      save().entities(parent, customer).now();
      return key;
    }
  }

  Key<DepartmentParent> loadDepartmentOrCreate(final Key<CustomerParent> customer, final String departmentName) {
    final Key<DepartmentParent> key = createDepartmentKey(customer, departmentName);
    if (Objects.nonNull(load().key(key).now())) {
      return key;
    } else {
      final DepartmentParent parent = new DepartmentParent(customer, departmentName);
      final Department department = new Department(customer, departmentName);
      save().entities(parent, department).now();
      return key;
    }
  }

  private Key<DepartmentParent> createDepartmentKey(final Key<CustomerParent> customer, final String name) {
    return Key.create(customer, DepartmentParent.class, name);
  }

  private Key<User> createUserKey(final Key<DepartmentParent> department, final String name) {
    return Key.create(Key.create(department, UserParent.class, name), User.class, name);
  }

  protected Saver save() {
    return ObjectifyService.ofy().save();
  }

  protected Loader load() {
    return ObjectifyService.ofy().load();
  }

  protected Deleter delete() {
    return ObjectifyService.ofy().delete();
  }
}
