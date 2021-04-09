package eu.ideell.api.mongodb;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;

import com.mongodb.client.result.DeleteResult;

import eu.ideell.api.mongodb.entity.Admin;
import eu.ideell.api.mongodb.entity.AdminInvitation;
import eu.ideell.api.mongodb.entity.Customer;
import eu.ideell.api.mongodb.entity.Department;
import eu.ideell.api.mongodb.entity.User;
import eu.ideell.api.mongodb.entity.UserInvitation;
import eu.ideell.api.spring.security.UnauthorizedException;
import se.cewebab.stockholm.util.Log;

public abstract class AbstractSecureMongoDb {

  @Autowired
  private MongoOperations operations;

  private static final Log log = new Log("AbstractSecureDatastore");

  Admin loadAdminOrCreate(final String subject, final Supplier<Auth0UserInfo> getUserInfo, final Supplier<String> getFirstEmail) {
    return Optional.ofNullable(load(Admin.class, getUserInfo.get().getSub()))
        .orElseGet(() -> {
          final Auth0UserInfo userInfo = getUserInfo.get();
          final AdminInvitation invitation = load(AdminInvitation.class, userInfo.getEmail());
          if (Objects.nonNull(invitation) || userInfo.getEmail().equals(getFirstEmail.get())) {
            log.log("Creating ADMIN with subject " + userInfo.getSub());
            return save(new Admin(userInfo));
          } else {
            throw new UnauthorizedException();
          }
        })
        ;
  }

  User loadUserOrCreate(final String subject, final Supplier<Auth0UserInfo> getUserInfo, final String customerName, final String departmentName) {
    final Customer customer = loadCustomerOrCreate(customerName);
    loadDepartmentOrCreate(customerName, departmentName);
    return Optional.ofNullable(load(User.class, subject))
        .orElseGet(() -> {
            final Auth0UserInfo userInfo = getUserInfo.get();
            if (customer.isInvitationMandatoryForNewUsers()) {
              final UserInvitation invitation = load(UserInvitation.class, userInfo.getEmail());
              if (Objects.isNull(invitation)) {
                throw new UnauthorizedException();
              }
            }
            log.log("Creating user with subject " + userInfo.getSub());
            return save(new User(customerName, departmentName, userInfo));
        })
        ;
  }

  Customer loadCustomerOrCreate(final String customerName) {
    return Optional.ofNullable(load(Customer.class, customerName))
        .orElseGet(() -> save(new Customer(customerName)))
        ;
  }

  Department loadDepartmentOrCreate(final String customerName, final String departmentName) {
    return Optional.ofNullable(load(Department.class, departmentName))
        .orElseGet(() -> save(new Department(customerName, departmentName)))
        ;
  }

  @Cacheable
  protected <Entity> Entity load(final Class<Entity> klass, final Object id) {
    return operations.findById(id, klass);
  }

  @Cacheable
  protected <Entity> Entity save(final Entity entity) {
    return operations.save(entity);
  }

  @Cacheable
  protected <Entity> DeleteResult delete(final Entity entity) {
    return operations.remove(entity);
  }
}
