package eu.ideell.api.model;

import java.util.List;

import lombok.Data;

@Data
public class MyViData {

  private IdeellAccount ideellAccount;
  private ViAccount viAccount;
  private List<ViPost> posts;
  private List<ViComment> comments;
}
