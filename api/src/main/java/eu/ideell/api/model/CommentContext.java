package eu.ideell.api.model;

import java.util.List;

import lombok.Data;

@Data
public class CommentContext {

  private AccountModeration history;
  private AccountI commenter;
  private CommentI comment;
  private List<Long> moderationChatMessageIds;
}
