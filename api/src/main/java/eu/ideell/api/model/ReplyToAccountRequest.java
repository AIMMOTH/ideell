package eu.ideell.api.model;

import java.util.Optional;

import lombok.Data;

@Data
public class ReplyToAccountRequest {

  private enum ReplyToAccountRequestType {
    accept, acceptAndTrustUser, reply, shadowbanAndReply, declineAndReply
    ;
  }
  private ReplyToAccountRequestType type;
  private Optional<String> reply;
}
