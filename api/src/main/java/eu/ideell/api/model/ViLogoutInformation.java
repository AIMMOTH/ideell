package eu.ideell.api.model;

import lombok.Data;

@Data
public class ViLogoutInformation {

  private Long msSinceLogin;
  private Integer nofWrittenPosts;
  private Integer nofWrittenComments;
  private Integer nofMadeReactions;
  private Integer nofLoadedPosts;
  private Integer nofLoadedComments;
  private Integer nofClickedLinks;
}
