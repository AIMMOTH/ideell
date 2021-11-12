package eu.ideell.api.mariadb.analytics;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnalyticsApi {

  @Autowired
  private AnalyticsRepository repository;

  @GetMapping("analytics")
  public List<AnalyticsResource> get() {
    return repository.findAll().stream()
        .map(AnalyticsResource::new)
        .collect(Collectors.toList())
        ;
  }

  @PostMapping("analytics")
  public void postAnalytics(@RequestBody final AnalyticsResource resource) {
    repository.save(new AnalyticsRow(resource));
  }
}
