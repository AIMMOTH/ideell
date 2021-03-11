package eu.ideell.api.model;

import java.util.Optional;

import lombok.Data;

@Data
public class SearchItem {

  public enum SearchType {
    post, contact
    ;
  }

  private SearchType type;
  private Optional<Long> viPostId;
  private Optional<Long> viAccountId;
}
