package eu.ideell.api.model;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
public class KvitterAccount {

  @NonNull
  private String publicNickname;
  private URL publicProfileImage;
  private List<Long> followGroupIds = Arrays.asList();
  private boolean acceptedUserAgreement = false;
}
