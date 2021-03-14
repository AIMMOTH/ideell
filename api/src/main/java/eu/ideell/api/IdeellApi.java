package eu.ideell.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.model.AccountI;
import eu.ideell.api.model.AnalyticsAction;
import eu.ideell.api.model.CommentContext;
import eu.ideell.api.model.Feedback;
import eu.ideell.api.model.IdeellAccount;
import eu.ideell.api.model.PostContext;
import eu.ideell.api.model.ReplyToAccountRequest;
import eu.ideell.api.model.ReplyToCommentRequest;
import eu.ideell.api.model.ReplyToPostRequest;
import eu.ideell.api.model.ReplyToReportRequest;
import eu.ideell.api.model.ReportI;
import eu.ideell.api.service.IdeellService;

@Controller("ideell")
public class IdeellApi {

  @Autowired
  private IdeellService service;

  @PostMapping("account")
  public long postAccount(final IdeellAccount resource) {
    return service.createAccount(resource);
  }

  @GetMapping("me")
  public IdeellAccount getMe() {
    return service.getAccount();
  }

  @PostMapping("feedback")
  public void postFeedback(final Feedback resource) {
    service.save(resource);
  }

  @PostMapping("analytics")
  public void postAnalytics(final AnalyticsAction resource) {
    service.removeIdsAndSave(resource);
  }

  @GetMapping("list-new-accounts")
  public List<AccountI> getNewAccounts() {
    return service.getNewAccounts();
  }

  @GetMapping("list-unaccepted-accounts")
  public List<AccountI> getUnacceptedAccounts() {
    return service.getUnacceptedAccounts();
  }

  @PostMapping("reply-to-account-request")
  public long postReplyToAccountRequest(final ReplyToAccountRequest resource) {
    return service.createReplyToAccountRequest(resource);
  }

  @GetMapping("list-new-posts")
  public List<PostContext> getNewPosts() {
    return service.getNewPosts();
  }

  @GetMapping("list-unaccepted-posts")
  public List<PostContext> getUnacceptedPosts() {
    return service.getUnacceptedPosts();
  }

  @PostMapping("reply-to-post-request")
  public long postReplyToPostRequest(final ReplyToPostRequest resource) {
    return service.createReplyToPostRequest(resource);
  }

  @GetMapping("list-new-comments")
  public List<CommentContext> getNewComments() {
    return service.getNewComments();
  }

  @GetMapping("list-unaccepted-comments")
  public List<CommentContext> getUnacceptedComments() {
    return service.getUnacceptedComments();
  }

  @PostMapping("reply-to-comment-request")
  public long postReplyToCommentRequest(final ReplyToCommentRequest resource) {
    return service.createReplyToCommentRequest(resource);
  }

  @GetMapping("list-new-reports")
  public List<ReportI> getNewReports() {
    return service.getNewReports();
  }

  @GetMapping("list-unaccepted-reports")
  public List<ReportI> getUnacceptedReports() {
    return service.getUnacceptedReports();
  }

  @PostMapping("reply-to-report-request")
  public long postReplyToReportRequest(final ReplyToReportRequest resource) {
    return service.createReplyToReportRequest(resource);
  }
}
