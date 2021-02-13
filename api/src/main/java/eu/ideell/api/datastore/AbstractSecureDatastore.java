package eu.ideell.api.datastore;

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

import eu.ideell.api.datastore.entity.Admin;
import eu.ideell.api.datastore.entity.AdminInvitation;
import eu.ideell.api.datastore.entity.AdminParent;
import eu.ideell.api.datastore.entity.Customer;
import eu.ideell.api.datastore.entity.Department;
import eu.ideell.api.datastore.entity.User;
import eu.ideell.api.datastore.entity.UserInvitation;
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
    final Key<Customer> customer = loadCustomerOrCreate(customerName);
    final Key<Department> department = loadDepartmentOrCreate(customer, departmentName);
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
            final User user = new User(department, userInfo);
            save().entity(user).now();
            return user;
          } else {
            return entity;
          }
        })
        .get()
        ;
  }

  Key<Customer> loadCustomerOrCreate(final String customerName) {
    final Key<Customer> key = Key.create(Customer.class, customerName);
    if (Objects.nonNull(load().key(key).now())) {
      return key;
    } else {
      final Customer customer = new Customer(customerName);
      save().entities(customer).now();
      return key;
    }
  }

  Key<Department> loadDepartmentOrCreate(final Key<Customer> customer, final String departmentName) {
    final Key<Department> key = createDepartmentKey(customer, departmentName);
    if (Objects.nonNull(load().key(key).now())) {
      return key;
    } else {
      final Department department = new Department(customer, departmentName);
      save().entities(department).now();
      return key;
    }
  }

  private Key<Department> createDepartmentKey(final Key<Customer> customer, final String name) {
    return Key.create(customer, Department.class, name);
  }

  private Key<User> createUserKey(final Key<Department> department, final String name) {
    return Key.create(department, User.class, name);
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
