package eu.ideell.api.submission;

import eu.ideell.api.mongodb.entity.SubmissionDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

  public SubmissionResource(final SubmissionDocument entity) {
    emailForContact = entity.getEmailForContact();
    feedback = entity.getFeedback();
    name = entity.getName();
    requestedNickname = entity.getRequestedNickname();
    interestedInDonation = entity.isInterestedInDonation() ? "on": "";
    interestedInFreeKonst = entity.isInterestedInFreeKonst() ? "on": "";
    interestedInFreeKvitter = entity.isInterestedInFreeKvitter() ? "on": "";
    interestedInFreeVi = entity.isInterestedInFreeVi() ? "on": "";
    interestedInIdeellMembership = entity.isInterestedInIdeellMembership() ? "on": "";
    interestedInSponsorship = entity.isInterestedInSponsorship() ? "on": "";
  }
}
