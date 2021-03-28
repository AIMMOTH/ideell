package eu.ideell.api.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import eu.ideell.api.service.model.SubmissionResource;

@RestController
public class SearchSubmissionApi {

  @Autowired
  private SearchSubmissionService service;

  @GetMapping("search-submissions")
  public List<SubmissionResource> get(@RequestParam("text") final String text) {
    return service.searchWithText(text);
  }
}
