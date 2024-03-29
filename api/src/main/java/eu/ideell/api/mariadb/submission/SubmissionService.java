package eu.ideell.api.mariadb.submission;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.RedirectView;

import eu.ideell.api.mariadb.searchsubmission.SearchSubmissionRepository;
import eu.ideell.api.mariadb.searchsubmission.SearchSubmissionRow;
import eu.ideell.api.mariadb.searchsubmission.SubmissionDocument;
import eu.ideell.api.util.Monad;

@Service
public class SubmissionService {

  private Logger logger = LoggerFactory.getLogger(SubmissionApi.class);
  @Autowired
  private SearchSubmissionRepository repository;
  @Autowired
  private MongoOperations operations;

  public List<SubmissionRow> getSubmissions() {
    return operations.findAll(SubmissionDocument.class).stream().map(SubmissionRow::new).collect(Collectors.toList());
  }

  public RedirectView createSubmission(final SubmissionRow resource) {
    final long count = operations.estimatedCount(SubmissionDocument.class);
    Monad.monad(new SubmissionDocument(count, resource))
      .accept(entity -> {
        final long submissionId = operations.save(entity).getSubmissionId();
        final int id = save(new SearchSubmissionRow(submissionId, entity)).getId();

        final String log = String.format("Saved entity with submission id %d and document id %d", submissionId, id);
        logger.info(log);
      })
      ;
    return new RedirectView("/index.html");
  }

  SearchSubmissionRow save(final SearchSubmissionRow searchSubmissionRow) {
    return repository.save(searchSubmissionRow);
  }
}
