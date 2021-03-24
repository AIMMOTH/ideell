package eu.ideell.api.spring;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import eu.ideell.api.mongodb.entity.SubmissionDocument;
import eu.ideell.api.service.model.SubmissionResource;

@Service
public class SqlSearchService {

  @Autowired
  private SqlSearchDocumentRepository repository;
  @Autowired
  private MongoOperations mongo;

  public List<SubmissionResource> searchWithText(final String text) {
    return repository.queryByText(text).stream()
      .map(id -> mongo.findById(id, SubmissionDocument.class))
      .map(SubmissionResource::new)
      .collect(Collectors.toList())
      ;
  }

  public Optional<SubmissionResource> findWithId(final Integer id) {
    return repository.findById(id)
        .map(SubmissionRow::getSubmissionId)
        .map(sqlId -> mongo.findById(sqlId, SubmissionDocument.class))
        .map(SubmissionResource::new)
        ;
  }

}
