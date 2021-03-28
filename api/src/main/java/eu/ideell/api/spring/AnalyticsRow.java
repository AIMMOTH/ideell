package eu.ideell.api.spring;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import eu.ideell.api.model.AnalyticsResource;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Carl
 *
 */
@Entity
@NoArgsConstructor
@Table
@Data
public class AnalyticsRow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Date created = new Date();
  private String currentUrl;
  private String linkPressed;
  private Long msSinceLastActivity;
  private String nameOfAction;
  private String previousLink;
  private Date timeOfAction;


  public AnalyticsRow(final AnalyticsResource resource) {
    currentUrl = resource.getCurrentUrl();
    linkPressed = resource.getLinkPressed();
    msSinceLastActivity = resource.getMsSinceLastActivity();
    nameOfAction = resource.getNameOfAction();
    previousLink = resource.getPreviousLink();
    timeOfAction = resource.getTimeOfAction();
  }
}
