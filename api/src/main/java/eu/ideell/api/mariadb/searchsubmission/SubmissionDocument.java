package eu.ideell.api.mariadb.searchsubmission;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import eu.ideell.api.mariadb.submission.SubmissionRow;
import lombok.Data;

@Data
@Document
public class SubmissionDocument {

  @Id
  private Long submissionId;
  @Indexed
  private LocalDateTime created = LocalDateTime.now();
  private boolean interestedInIdeellMembership;
  private boolean interestedInFreeKonst;
  private boolean interestedInFreeKvitter;
  private boolean interestedInFreeVi;
  private boolean interestedInDonation;
  private boolean interestedInSponsorship;
  private String emailForContact;
  private String feedback;
  private String name;
  private String requestedNickname;

  public SubmissionDocument(final long id, final SubmissionRow resource) {
    submissionId = id;
    emailForContact = resource.getEmailForContact();
    feedback = resource.getFeedback();
    interestedInDonation = isOn(resource.getInterestedInDonation());
    interestedInFreeKonst = isOn(resource.getInterestedInFreeKonst());
    interestedInFreeKvitter = isOn(resource.getInterestedInFreeKvitter());
    interestedInFreeVi = isOn(resource.getInterestedInFreeVi());
    interestedInIdeellMembership = isOn(resource.getInterestedInIdeellMembership());
    interestedInSponsorship = isOn(resource.getInterestedInSponsorship());
    name = resource.getName();
    requestedNickname = resource.getRequestedNickname();
  }

  private boolean isOn(final String text) {
    return Objects.equals("on", text);
  }
}
