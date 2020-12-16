package eu.euphoros.api.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;

import eu.euphoros.api.datastore.entity.Admin;
import eu.euphoros.api.datastore.entity.AdminParent;
import eu.euphoros.api.datastore.entity.Customer;
import eu.euphoros.api.datastore.entity.CustomerParent;
import eu.euphoros.api.datastore.entity.Department;
import eu.euphoros.api.datastore.entity.DepartmentParent;
import eu.euphoros.api.datastore.entity.User;
import eu.euphoros.api.datastore.entity.UserInvitation;
import eu.euphoros.api.datastore.entity.UserParent;
import eu.euphoros.api.datastore.entity.VisionResult;
import eu.euphoros.api.service.model.Product;
import se.cewebab.stockholm.servlet.DefaultServletConfig;

@WebListener
public class ServletConfig extends DefaultServletConfig {

  @Override
  public void contextInitialized(final ServletContextEvent event) {
    super.contextInitialized(event);
    register();
  }

  public static void register() {
    ObjectifyService.register(Admin.class);
    ObjectifyService.register(AdminParent.class);
    ObjectifyService.register(Customer.class);
    ObjectifyService.register(CustomerParent.class);
    ObjectifyService.register(Department.class);
    ObjectifyService.register(DepartmentParent.class);
    ObjectifyService.register(Product.class);
    ObjectifyService.register(User.class);
    ObjectifyService.register(UserInvitation.class);
    ObjectifyService.register(UserParent.class);
    ObjectifyService.register(VisionResult.class);
  }

  @Override
  public void contextDestroyed(final ServletContextEvent event) {
  }

  @Override
  protected String getSettingsFile() {
    return "/" + systemProperties.getEnvironment() + ".json";
  }
}