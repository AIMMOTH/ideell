package eu.ideell.api.spring.submission;

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

import eu.ideell.api.service.model.SubmissionResource;

@RestController("submission-api")
public class SubmissionApi {

  @Autowired
  private SubmissionService service;
  @Autowired
  private CacheManager cache;

  @Cacheable("get-submissions")
  @GetMapping("get-submissions")
  public List<SubmissionResource> getSubmissions(@RequestHeader final Map<String, String> headers) {
    return service.getSubmissions();
  }

//  @CacheEvict("get-submissions")
  @PostMapping("submission")
  public RedirectView postSubmission(@ModelAttribute final SubmissionResource resource) {
    cache.getCache("get-submissions").clear();
    return service.createSubmission(resource);
  }

}