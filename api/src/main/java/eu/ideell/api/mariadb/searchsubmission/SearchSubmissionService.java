package eu.ideell.api.mariadb.searchsubmission;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import eu.ideell.api.mariadb.submission.SubmissionRow;

@Service
public class SearchSubmissionService {

  @Autowired
  private SearchSubmissionRepository repository;
  @Autowired
  private MongoOperations mongo;

  @CachePut("search-submission")
  public List<SubmissionRow> searchWithText(final String text) {
    return repository.queryByText(text).stream()
      .map(id -> mongo.findById(id, SubmissionDocument.class))
      .map(SubmissionRow::new)
      .collect(Collectors.toList())
      ;
  }

}
