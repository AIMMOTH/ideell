package eu.ideell.api.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

import com.googlecode.objectify.ObjectifyService;

import eu.ideell.api.datastore.entity.Admin;
import eu.ideell.api.datastore.entity.AdminParent;
import eu.ideell.api.datastore.entity.Customer;
import eu.ideell.api.datastore.entity.Department;
import eu.ideell.api.datastore.entity.User;
import eu.ideell.api.datastore.entity.UserInvitation;
import eu.ideell.api.datastore.entity.UserParent;
import eu.ideell.api.datastore.entity.VisionResult;
import eu.ideell.api.service.model.Product;
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
    ObjectifyService.register(Department.class);
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