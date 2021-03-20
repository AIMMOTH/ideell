package eu.ideell.api.spring;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SqlSearchDocumentRepository extends JpaRepository<SqlSearchDocument, Integer> {

  @Query("select id from SqlSearchDocument table where table.textOriginal like %:text% or table.textNormalized like %:text% or table.textLowerCase like %:text%")
  public List<Integer> queryByText(String text);

}
