package eu.euphoros.api.service;

import javax.inject.Inject;

import eu.euphoros.api.service.model.ChatMessage;
import se.cewebab.stockholm.messaging.Messaging;

public class Chat {

  @Inject
  private Messaging messaging;

  public void push(final ChatMessage message) {
    messaging.sendMessage(message.getToChatChannelName(), "euphoros-ideell-social-media-message-event", message.getMessage());
  }

}
