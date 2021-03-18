package eu.ideell.api.spring;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import eu.ideell.api.model.AnalyticsResource;
import eu.ideell.api.mongodb.entity.Analytics;

@RestController
public class AnalyticsApi {

  @Autowired
  private MongoOperations operations;

  @GetMapping("analytics")
  public List<AnalyticsResource> get() {
    return operations.findAll(Analytics.class).stream()
        .map(AnalyticsResource::new)
        .collect(Collectors.toList())
        ;
  }

  @PostMapping("analytics")
  public void postAnalytics(@RequestBody final AnalyticsResource resource) {
    final long count = operations.estimatedCount(Analytics.class);
    operations.save(new Analytics(count, resource));
  }
}
