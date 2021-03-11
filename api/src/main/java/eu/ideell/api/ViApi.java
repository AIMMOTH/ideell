package eu.ideell.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import eu.ideell.api.model.MyViData;
import eu.ideell.api.model.RequestAddContact;
import eu.ideell.api.model.SaveDirectMessageId;
import eu.ideell.api.model.SearchItem;
import eu.ideell.api.model.ViAccount;
import eu.ideell.api.model.ViComment;
import eu.ideell.api.model.ViLogoutInformation;
import eu.ideell.api.model.ViMessages;
import eu.ideell.api.model.ViPost;
import eu.ideell.api.model.ViSearch;
import eu.ideell.api.service.ViService;

@Controller("vi")
public class ViApi {

  @Autowired
  private ViService service;

  @PostMapping("account")
  public long postAccount(final ViAccount resource) {
    return service.createAccount(resource);
  }

  @GetMapping("me")
  public ViAccount getMe() {
    return service.readMe();
  }

  @PutMapping("logout")
  public ViLogoutInformation logout() {
    return service.logout();
  }

  @PutMapping("request-add-contact")
  public void putRequestAddContact(final RequestAddContact resource) {
    service.updateRequestAddContact(resource);
  }

  @GetMapping("messages")
  public List<ViMessages> getMessages() {
    return service.readMessages();
  }

  @PostMapping("post")
  public long post(final ViPost resource) {
    return service.post(resource);
  }

  @GetMapping("feed")
  public List<ViPost> getFeed(final String fromId) {
    return service.readFeed(fromId);
  }

  @PostMapping
  public List<SearchItem> postSearch(final ViSearch resource) {
    return service.createSearch(resource);
  }

  @GetMapping("post")
  public ViPost getPost(final long viPostId) {
    return service.readPost(viPostId);
  }

  @GetMapping("account")
  public ViAccount getAccount(final long viAccountId) {
    return service.readAccount(viAccountId);
  }

  @GetMapping("account/{accountId}/feed")
  public List<ViPost> getAccountFeed(final long viAccountId) {
    return service.readAccountFeed(viAccountId);
  }

  @DeleteMapping("my-post")
  public void deleteMyPost(final long viPostId) {
    service.deleteMyPost(viPostId);
  }

  @PostMapping("report-post")
  public void reportPost(final long viPostId) {
    service.reportPost(viPostId);
  }

  @PostMapping("comment-on-post")
  public long postCommentOnPost(final long viPostId, final ViComment resource) {
    return service.createCommentOnPost(viPostId, resource);
  }

  @PostMapping("comment-on-post")
  public long postCommentOnComment(final long viCommentId, final ViComment resource) {
    return service.createCommentOnComment(viCommentId, resource);
  }

  @PostMapping("report-comment")
  public void reportComment(final long viCommentId) {
    service.reportPost(viCommentId);
  }

  @PutMapping("save-direct-messaging-id")
  public void saveDirectMessageId(final SaveDirectMessageId resource) {
    service.updateDirectMessageId(resource);
  }

  @GetMapping("direct-messaging-ids")
  public List<SaveDirectMessageId> getDirectMessageIds(final long viAccountId) {
    return service.readDirectMessageIdsFromMyContact(viAccountId);
  }

  @GetMapping("my-data")
  public MyViData getMyData() {
    return service.readMyData();
  }

  @DeleteMapping("my-data")
  public void deleteMyData() {
    service.deleteMyData();
  }

  @DeleteMapping("my-account")
  public void deleteMyAccount() {
    service.deleteMyAccount();
  }
}
