package eu.ideell.api.model;

import lombok.Data;

@Data
public class ModerationChatMessage {

  private String moderatorIdeellId;
  private String userIdeellId;
  private String message;
}
