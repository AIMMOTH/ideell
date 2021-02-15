package eu.ideell.api.mongodb.entity;

import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import se.cewebab.stockholm.vision.model.Result;

@Data
@Document
@NoArgsConstructor
public class VisionResult {

  @Id
  private Long visionResultId;
  @Indexed
  private Date created = new Date();
  private List<Result> results;

  public VisionResult(final List<Result> results) {
    this.results = results;
  }
}
