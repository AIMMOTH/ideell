package eu.ideell.api.spring.heartbeat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.ideell.api.service.model.HeartbeatResource;

@RestController("heartbeat-api")
public class HeartbeatApi {

  @Value("${application.name}")
  private String applicationName;
  @Value("${application.environment}")
  private String applicationEnvironment;
  @Value("${cors}")
  private String corsValue;

  @GetMapping("ping")
  public String getPing() {
    return "pong";
  }

  @GetMapping("heartbeat")
  public HeartbeatResource getHeartbeat() {
    return new HeartbeatResource(applicationName, applicationEnvironment, corsValue);
  }
}
