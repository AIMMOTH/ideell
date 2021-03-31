package eu.ideell.api.model;

import java.net.URL;
import java.util.List;

import com.google.common.collect.Lists;
import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
public class KvitterAccount {

  @NonNull
  private String publicNickname;
  private URL publicProfileImage;
  private List<Long> followGroupIds = Lists.newArrayList();
  private boolean acceptedUserAgreement = false;
}
