package eu.ideell.api.model;

import java.util.Optional;

import lombok.Data;

@Data
public class ViMessages {

  public enum MessageType {
    moderationMessage, addContactSuccessful, connectionRequest,
    postMention, commentMention, reported, reportFeedback
    ;
  }

  private MessageType type;
  private Optional<String> message;
  private Optional<Long> otherContactViAccountId;
  private Optional<Long> postId;
  private Optional<Long> commentId;
  private Optional<Long> reportId;
}
