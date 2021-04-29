package eu.ideell.api.heartbeat;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Data
@PropertySource("classpath:application.properties")
public class HeartbeatResource {

  private String applicationName;
  private String applicationEnvironment;
  private String corsValue;
  private Date systemTime = new Date();
  private LocalDateTime systemLocalTime = LocalDateTime.now();

  public HeartbeatResource(final String applicationName, final String applicationEnvironment, final String corsValue) {
    this.applicationName = applicationName;
    this.applicationEnvironment = applicationEnvironment;
    this.corsValue = corsValue;
  }
}
