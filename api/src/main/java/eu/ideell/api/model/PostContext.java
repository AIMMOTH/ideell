package eu.ideell.api.model;

import java.util.List;

import lombok.Data;

@Data
public class PostContext {

  private AccountModeration history;
  private AccountI poster;
  private PostI post;
  private List<Long> moderationChatMessageIds;

}
