package eu.ideell.api.model;

import lombok.Data;

@Data
public class Feedback {

  private Boolean interestedInVi;
  private Boolean interestedInKonst;
  private Boolean interestedInKvitter;
  private Boolean interestedInTraining;
  private Boolean interestedInDating;
  private Boolean interestedInXxx;
  private Boolean interestedInTodo;
  private Boolean interestedInCalendar;
  private Boolean interestedInMonthlyIdeellMembership;
  private String feedback;
}
