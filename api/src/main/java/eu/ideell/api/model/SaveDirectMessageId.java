package eu.ideell.api.model;

import lombok.Data;

@Data
public class SaveDirectMessageId {

  public enum DirectMessageServiceType {
    signal, whatsapp, messenger, hangouts, email, phone, telegram
    ;
  }

  private DirectMessageServiceType service;
  private String id;
}
