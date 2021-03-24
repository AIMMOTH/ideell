package eu.ideell.api.model;

import java.util.Date;

import eu.ideell.api.mongodb.entity.AnalyticsDocument;
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

  public AnalyticsResource(final AnalyticsDocument entity) {
    this.currentUrl = entity.getCurrentUrl();
    this.linkPressed = entity.getLinkPressed();
    this.msSinceLastActivity = entity.getMsSinceLastActivity();
    this.nameOfAction = entity.getNameOfAction();
    this.timeOfAction = entity.getTimeOfAction();
    this.previousLink = entity.getPreviousLink();
  }
}
