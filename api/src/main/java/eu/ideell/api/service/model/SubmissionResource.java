package eu.ideell.api.service.model;

import lombok.Data;

@Data
public class SubmissionResource {

  private String interestedInIdeellMembership;
  private String interestedInFreeKonst;
  private String interestedInFreeKvitter;
  private String interestedInFreeVi;
  private String interestedInDonation;
  private String interestedInSponsorship;
  private String emailForContact;
  private String feedback;
  private String name;
  private String requestedNickname;
}
