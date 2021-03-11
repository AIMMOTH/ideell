package eu.ideell.api.model;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Optional;

import lombok.Data;

@Data
public class AnalyticsAction {

  private String nameOfAction;
  private LocalDateTime timeOfAction;
  private URL currentUrl;
  private Optional<URL> linkPressed;
  private Optional<URL> previousLink;
  private Optional<Long> msSinceLastActivity;
}
