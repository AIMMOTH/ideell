package eu.ideell.api.datastore;

import java.util.function.Supplier;

import javax.ws.rs.NotAuthorizedException;

import org.junit.Assert;
import org.junit.Test;

import com.googlecode.objectify.Key;

import eu.ideell.api.datastore.entity.Admin;
import eu.ideell.api.datastore.entity.AdminParent;
import eu.ideell.api.datastore.entity.Customer;
import eu.ideell.api.datastore.entity.Department;
import eu.ideell.api.datastore.entity.User;
import eu.ideell.api.datastore.entity.UserInvitation;
import eu.ideell.api.datastore.entity.UserParent;
import eu.ideell.api.jersey.TestJersey;

public class TestAbstractSecureDatastore extends TestJersey {

  @Test
  public void test_loadAdminOrCreate_withExisting() {
    // Given
    final String subject = "auth0 id";
    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, "not-used");

    final AdminParent parent = new AdminParent(subject);
    final Admin admin = new Admin(Key.create(AdminParent.class, subject), getInfo.get());
    testSave().entities(admin, parent).now();
    final Supplier<String> getFirstEmail = () -> "not-called";
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };

    // When
    final Admin result = datastore.loadAdminOrCreate(subject, getInfo, getFirstEmail);

    // Then
    Assert.assertEquals(admin, result);
  }

  @Test
  public void test_loadAdminOrCreate_withNotInvited() {
    // Given
    final String subject = "auth0 id";
    final String email1 = "not-invited-admin@epost.se";
    final String email2 = "invited-admin@epost.se";
    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, email1);
    final Supplier<String> getFirstEmail = () -> email2;
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };

    // When
    try {
      datastore.loadAdminOrCreate(subject, getInfo, getFirstEmail);
      Assert.fail();
    } catch (final NotAuthorizedException e) {
      // Then
    }
  }

  @Test
  public void test_loadAdminOrCreate_withInvited() {
    // Given
    final String subject = "auth0 id";
    final String email = "admin@epost.se";
    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, email);
    final Supplier<String> getEmail = () -> email;
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };

    // When
    final Admin admin = datastore.loadAdminOrCreate(subject, getInfo, getEmail);

    // Then
    Assert.assertNotNull(admin);
  }

  @Test
  public void test_loadUserOrCreate_withExisting() {
    // Given
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };;

    final String customerName = "customer name";
    final String departmentName = "department name";
    final String subject = "auth0 id";

    final Key<Customer> customerParentKey = datastore.loadCustomerOrCreate(customerName);
    final Key<Department> departmentParentKey = datastore.loadDepartmentOrCreate(customerParentKey, departmentName);
    final Key<UserParent> userParentKey = Key.create(departmentParentKey, UserParent.class, subject);

    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, "not-used");

    final User user = new User(departmentParentKey, getInfo.get());

    testSave().entities(user).now();

    // When
    final User result = datastore.loadUserOrCreate(subject, getInfo, customerName, departmentName);

    // Then
    Assert.assertEquals(user, result);
  }

  @Test
  public void test_loadUserOrCreate_withExistingNotInvited() {
    // Given
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };

    final String customerName = "customer name";
    final String departmentName = "department name";
    final String subject = "auth0 id";
    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, "not-used");

    final Key<Customer> customerParentKey = datastore.loadCustomerOrCreate(customerName);
    final Key<Department> departmentParentKey = datastore.loadDepartmentOrCreate(customerParentKey, departmentName);

    final Customer customer = testLoad(Key.create(customerParentKey, Customer.class, customerName));
    customer.setInvitationMandatoryForNewUsers(true);

    testSave().entities(customer).now();

    // When
    try {
      datastore.loadUserOrCreate(subject, getInfo, customerName, departmentName);
      Assert.fail();
    } catch (final NotAuthorizedException e) {
      // Then
    }
  }

  @Test
  public void test_loadUserOrCreate_withExistingInvited() {
    // Given
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };

    final String customerName = "customer name";
    final String departmentName = "department name";
    final String subject = "auth0 id";
    final String invitationEmail = "user@email.com";
    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, invitationEmail);

    final Key<Customer> customerParentKey = datastore.loadCustomerOrCreate(customerName);
    final Key<Department> departmentParentKey = datastore.loadDepartmentOrCreate(customerParentKey, departmentName);
    final UserInvitation invitation = new UserInvitation(invitationEmail);

    final Customer customer = testLoad(Key.create(customerParentKey, Customer.class, customerName));
    customer.setInvitationMandatoryForNewUsers(true);

    testSave().entities(customer, invitation).now();

    // When
    final User user = datastore.loadUserOrCreate(subject, getInfo, customerName, departmentName);

    // Then
    Assert.assertNotNull(user);
  }

  @Test
  public void test_loadUserOrCreate() {
    // Given
    final String subject = "auth0 id";
    final String email = "admin@epost.se";
    final Supplier<Auth0UserInfo> getInfo = () -> new Auth0UserInfo(subject, email);
    final AbstractSecureDatastore datastore = new AbstractSecureDatastore() {
    };

    // When
    final User user = datastore.loadUserOrCreate(subject, getInfo, "default", "default");

    // Then
    Assert.assertNotNull(user);
  }
}
