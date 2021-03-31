package eu.ideell.api.model;

import java.util.List;

import com.google.common.collect.Lists;

import lombok.Data;

@Data
public class ViAccount implements AccountI {

  private List<Long> followGroupIds = Lists.newArrayList();
  private boolean acceptedUserAgreement = false;
}
