package eu.ideell.api.submission;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController("Submission API")
public class SubmissionApi {

  @Autowired
  private SubmissionService service;
  @Autowired
  private CacheManager cache;

  @Cacheable("get-submissions")
  @GetMapping("/api/v1/public/get-submissions")
  public List<SubmissionResource> getSubmissions(@RequestHeader final Map<String, String> headers) {
    return service.getSubmissions();
  }

//  @CacheEvict("get-submissions")
  @PostMapping("/api/v1/public/submission")
  public RedirectView postSubmission(@ModelAttribute final SubmissionResource resource) {
    cache.getCache("get-submissions").clear();
    return service.createSubmission(resource);
  }

}
