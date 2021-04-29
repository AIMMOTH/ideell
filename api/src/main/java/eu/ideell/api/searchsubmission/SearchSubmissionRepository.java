package eu.ideell.api.searchsubmission;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SearchSubmissionRepository extends JpaRepository<SearchSubmissionRow, Integer> {

  @Query("select submissionId from SearchSubmissionRow table where table.textOriginal like %:text% or table.textNormalized like %:text% or table.textLowerCase like %:text%")
  public List<Integer> queryByText(String text);
}
