package eu.ideell.api.spring;

import java.util.Date;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import eu.ideell.api.mongodb.entity.SubmissionDocument;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CREATE TABLE `ideell`.`SearchDocumentRow`
 * ( `id` INT NOT NULL , `submissionId` INT NOT NULL ,
 * `textOriginal` VARCHAR(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
 * `textLowerCase` VARCHAR(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
 * `textNormalized` VARCHAR(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL ,
 * PRIMARY KEY (`id`)
 * ) ENGINE = InnoDB COMMENT = 'Text values are max 280 characters';
 * @author Carl
 *
 */
@Entity
@NoArgsConstructor
@Table
@Data
public class SearchSubmissionRow {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Date created = new Date();
  private Long submissionId;
  @Column(length = 280)
  private String textOriginal;
  @Column(length = 280)
  private String textLowerCase;
  @Column(length = 280)
  private String textNormalized;

  public SearchSubmissionRow(final long submissionId, final SubmissionDocument entity) {
    this.submissionId = submissionId;
    final String text = Optional.ofNullable(entity.getFeedback())
      .orElse("")
      ;
    textOriginal = text;
    textLowerCase = text.toLowerCase();
    textNormalized = StringUtils.deleteWhitespace(StringUtils.stripAccents(text.toLowerCase()));
  }
}
