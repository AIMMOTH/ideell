package eu.ideell.api.spring;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import eu.ideell.api.mongodb.entity.Submission;
import eu.ideell.api.service.model.SubmissionResource;

@RestController("submission-api")
public class SubmissionApi {

  @Autowired
  private MongoOperations operations;

  @GetMapping("get-submissions")
  public List<Submission> getSubmissions() {
    return operations.findAll(Submission.class);
  }

  @PostMapping("submission")
  public RedirectView postSubmission(@ModelAttribute final SubmissionResource resource) {
    final long count = operations.estimatedCount(Submission.class);
    operations.save(new Submission(count, resource)).getSubmissionId();
    return new RedirectView("/index.html");
  }
}
