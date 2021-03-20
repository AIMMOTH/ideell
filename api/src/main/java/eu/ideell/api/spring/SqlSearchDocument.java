package eu.ideell.api.spring;

import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import eu.ideell.api.mongodb.entity.Submission;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "search_document")
@Data
public class SqlSearchDocument {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Long submissionId;
  private String textOriginal;
  private String textLowerCase;
  private String textNormalized;

  public SqlSearchDocument(final long submissionId, final Submission entity) {
    this.submissionId = submissionId;
    final String text = Optional.ofNullable(entity.getFeedback())
      .orElse("")
      ;
    textOriginal = text;
    textLowerCase = text.toLowerCase();
    textNormalized = StringUtils.deleteWhitespace(StringUtils.stripAccents(text.toLowerCase()));
  }
}
