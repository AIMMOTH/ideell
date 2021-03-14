package eu.ideell.api.model;

import java.util.Date;

import eu.ideell.api.type.ServiceType;
import lombok.Data;

@Data
public class Ban {

  private Long banId;
  private String accountIdeellId;
  private ServiceType service;
  private boolean shadowban;
  private boolean permanent;
  private Date activated;
  private int nofDays;
  private String moderatorComment;
}
