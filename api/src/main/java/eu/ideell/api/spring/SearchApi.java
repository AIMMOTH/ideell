package eu.ideell.api.spring;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ideell.api.service.model.SubmissionResource;

@RestController
public class SearchApi {

  @Autowired
  private SqlSearchService service;

  @GetMapping("search-submissions")
  public List<SubmissionResource> get(@RequestParam("text") final String text) {
    return service.searchWithText(text);
  }

  @GetMapping("get-submission")
  public Optional<SubmissionResource> get(@RequestParam("id") final int id) {
    return service.findWithId(id);
  }
}
