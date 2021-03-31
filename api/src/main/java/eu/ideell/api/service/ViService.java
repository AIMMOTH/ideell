package eu.ideell.api.service;

import java.util.List;

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

public interface ViService {

  long createAccount(ViAccount resource);

  ViAccount readMe();

  ViLogoutInformation logout();

  void updateRequestAddContact(RequestAddContact resource);

  List<ViMessages> readMessages();

  long post(ViPost resource);

  List<ViPost> readFeed(String fromId);

  List<SearchItem> createSearch(ViSearch resource);

  ViPost readPost(long viPostId);

  ViAccount readAccount(long viAccountId);

  List<ViPost> readAccountFeed(long viAccountId);

  void deleteMyPost(long viPostId);

  void reportPost(long viPostId);

  long createCommentOnPost(long viPostId, ViComment resource);

  long createCommentOnComment(long viCommentId, ViComment resource);

  void updateDirectMessageId(SaveDirectMessageId resource);

  List<SaveDirectMessageId> readDirectMessageIdsFromMyContact(long viAccountId);

  MyViData readMyData();

  void deleteMyData();

  void deleteMyAccount();

}
