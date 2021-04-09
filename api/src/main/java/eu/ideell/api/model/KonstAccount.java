package eu.ideell.api.model;

import java.net.URL;
import java.util.Arrays;
import java.util.List;

import com.mongodb.lang.NonNull;

import lombok.Data;

@Data
public class KonstAccount {

  @NonNull
  private String publicNickname;
  private URL publicProfileImage;
  private List<Long> followKonstGroupIds = Arrays.asList();
  private boolean acceptedUserAgreement = false;
}
