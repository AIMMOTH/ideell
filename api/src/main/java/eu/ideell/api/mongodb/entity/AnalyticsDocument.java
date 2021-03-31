package eu.ideell.api.mongodb.entity;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.ideell.api.model.AnalyticsResource;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class AnalyticsDocument {

  @Id
  private Long analyticsId;
  @Indexed
  private LocalDateTime created = LocalDateTime.now();
  private String currentUrl;
  private String linkPressed;
  private Long msSinceLastActivity;
  private String nameOfAction;
  private String previousLink;
  private Date timeOfAction;

  public AnalyticsDocument(final long analyticsId, final AnalyticsResource resource) {
    this.analyticsId = analyticsId;
    currentUrl = resource.getCurrentUrl();
    linkPressed = resource.getLinkPressed();
    msSinceLastActivity = resource.getMsSinceLastActivity();
    nameOfAction = resource.getNameOfAction();
    previousLink = resource.getPreviousLink();
    timeOfAction = resource.getTimeOfAction();
  }
}
