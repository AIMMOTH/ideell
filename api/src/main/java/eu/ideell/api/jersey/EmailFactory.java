package eu.ideell.api.jersey;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import se.cewebab.stockholm.email.Email;
import se.cewebab.stockholm.email.EmailWithSendGrid;
import se.cewebab.stockholm.util.Settings;

public class EmailFactory implements Factory<Email> {

  @Inject
  private Settings settings;

  @Override
  public Email provide() {
    return new EmailWithSendGrid(settings.getText("SendGrid", "API Key"));
  }

  @Override
  public void dispose(final Email instance) {
  }

}
