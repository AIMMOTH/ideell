package eu.ideell.api.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class SheetsResponse {

  private List<String> headers;
  private List<Row> rows;
}
