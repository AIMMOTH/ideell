package eu.ideell.api.mariadb.searchsubmission;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ideell.api.mariadb.submission.SubmissionRow;

@RestController
public class SearchSubmissionApi {

  @Autowired
  private SearchSubmissionService service;

  @Cacheable("search-submissions")
  @GetMapping("search-submissions")
  public List<SubmissionRow> get(@RequestParam("text") final String text) {
    return service.searchWithText(text);
  }
}
