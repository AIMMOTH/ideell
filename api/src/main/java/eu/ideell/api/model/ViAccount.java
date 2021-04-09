package eu.ideell.api.model;

import java.util.Arrays;
import java.util.List;

import lombok.Data;

@Data
public class ViAccount implements AccountI {

  private List<Long> followGroupIds = Arrays.asList();
  private boolean acceptedUserAgreement = false;
}
