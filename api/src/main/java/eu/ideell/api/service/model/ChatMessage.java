package eu.ideell.api.service.model;

import lombok.Data;

@Data
public class ChatMessage {

  private String toChatChannelName;
  private String message;
}
