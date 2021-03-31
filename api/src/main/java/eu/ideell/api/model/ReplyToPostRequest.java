package eu.ideell.api.model;

import java.util.Optional;

import lombok.Data;

@Data
public class ReplyToPostRequest {

  private enum ReplyToPostRequestType {
    accept, acceptAndTrustUser, reply, shadowbanAndReply, declineAndReply
    ;
  }
  private ReplyToPostRequestType type;
  private Optional<String> reply;
}
