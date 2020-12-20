package eu.ideell.api.service;

import javax.inject.Inject;

import eu.ideell.api.service.model.ChatMessage;
import se.cewebab.stockholm.messaging.Messaging;

public class Chat {

  @Inject
  private Messaging messaging;

  public void push(final ChatMessage message) {
    messaging.sendMessage(message.getToChatChannelName(), "ideell-message-event", message.getMessage());
  }

}
