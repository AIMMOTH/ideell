package eu.ideell.api.mariadb.analytics;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnalyticsResource {

  private String currentUrl;
  private String linkPressed;
  private Long msSinceLastActivity;
  private String nameOfAction;
  private Date timeOfAction;
  private String previousLink;

  public AnalyticsResource(final AnalyticsRow row) {
    this.currentUrl = row.getCurrentUrl();
    this.linkPressed = row.getLinkPressed();
    this.msSinceLastActivity = row.getMsSinceLastActivity();
    this.nameOfAction = row.getNameOfAction();
    this.timeOfAction = row.getTimeOfAction();
    this.previousLink = row.getPreviousLink();
  }
}
