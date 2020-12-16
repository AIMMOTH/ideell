package eu.euphoros.api.jersey;

import javax.inject.Inject;

import org.glassfish.hk2.api.Factory;

import se.cewebab.stockholm.messaging.Messaging;
import se.cewebab.stockholm.messaging.MessagingWithPusher;
import se.cewebab.stockholm.util.Settings;

public class MessageFactory implements Factory<Messaging> {

  @Inject
  private Settings settings;

  @Override
  public Messaging provide() {
    return new MessagingWithPusher(settings.getText("Pusher", "App Id"),
        settings.getText("Pusher", "Key"),
        settings.getText("Pusher", "Secret")
        );
  }

  @Override
  public void dispose(final Messaging instance) {

  }

}
