package eu.ideell.api.service;

import org.springframework.beans.factory.annotation.Autowired;

import eu.ideell.api.service.model.ChatMessage;
import se.cewebab.stockholm.messaging.Messaging;

public class Chat {

  @Autowired
  private Messaging messaging;

  public void push(final ChatMessage message) {
    messaging.sendMessage(message.getToChatChannelName(), "ideell-message-event", message.getMessage());
  }

}
