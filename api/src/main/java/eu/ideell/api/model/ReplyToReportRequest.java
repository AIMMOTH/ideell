package eu.ideell.api.model;

import java.util.Optional;

import lombok.Data;

@Data
public class ReplyToReportRequest {

  private enum ReplyToCommentRequestType {
    accept, acceptAndTrustUser, reply, shadowbanAndReply, declineAndReply
    ;
  }
  private ReplyToCommentRequestType type;
  private Optional<String> reply;
}
