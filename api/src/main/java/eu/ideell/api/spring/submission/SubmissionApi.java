package eu.ideell.api.spring.submission;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import eu.ideell.api.mongodb.entity.SubmissionDocument;
import eu.ideell.api.service.model.SubmissionResource;
import eu.ideell.api.spring.searchsubmission.SearchSubmissionRepository;
import eu.ideell.api.spring.searchsubmission.SearchSubmissionRow;
import se.cewebab.stockholm.util.Monad;

@RestController("submission-api")
public class SubmissionApi {

  private Logger logger = LoggerFactory.getLogger(SubmissionApi.class);
  @Autowired
  private SearchSubmissionRepository repository;
  @Autowired
  private MongoOperations operations;

  @Cacheable("submissions")
  @GetMapping("get-submissions")
  public List<SubmissionResource> getSubmissions() {
    return operations.findAll(SubmissionDocument.class).stream().map(SubmissionResource::new).collect(Collectors.toList());
  }

  @PostMapping("submission")
  public RedirectView postSubmission(@ModelAttribute final SubmissionResource resource) {
    final long count = operations.estimatedCount(SubmissionDocument.class);
    Monad.monad(new SubmissionDocument(count, resource))
      .accept(entity -> {
        final long submissionId = operations.save(entity).getSubmissionId();
        final int id = save(new SearchSubmissionRow(submissionId, entity)).getId();

        final String log = String.format("Saved entity with submission id %d and document id %d", submissionId, id);
        logger.info(log);
        System.out.println(log);
      })
      ;
    return new RedirectView("/index.html");
  }

  @CacheEvict(cacheNames = {"submissions", "search-submission"}, allEntries = true)
  private SearchSubmissionRow save(final SearchSubmissionRow searchSubmissionRow) {
    return repository.save(searchSubmissionRow);
  }
}
