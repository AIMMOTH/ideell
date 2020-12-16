package eu.euphoros.api.datastore.entity;

import java.util.Date;
import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import lombok.Data;
import lombok.NoArgsConstructor;
import se.cewebab.stockholm.vision.model.Result;

@Data
@Entity
@NoArgsConstructor
public class VisionResult {

  @Id
  private Long visionResultId;
  @Index
  private Date created = new Date();
  private List<Result> results;

  public VisionResult(final List<Result> results) {
    this.results = results;
  }
}
