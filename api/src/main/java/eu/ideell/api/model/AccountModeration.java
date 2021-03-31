package eu.ideell.api.model;

import java.util.List;

import lombok.Data;

@Data
public class AccountModeration {

  private int nofTemporaryBans;
  private int numberOfBannedDays;
  private int numberOfShadowbannedDays;
  private List<Long> banIds;
  private List<Long> viPostIds;
  private List<Long> viCommentIds;
}
