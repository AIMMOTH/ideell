package eu.ideell.api.model;

import java.util.Date;

import lombok.Data;

@Data
public class RequestAddContact {

  private Long addContactViAccountId;
  private Date requested = new Date();
}
