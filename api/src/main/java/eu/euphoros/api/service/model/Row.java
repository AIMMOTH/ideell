package eu.euphoros.api.service.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Row {

  private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  private Integer rowNumber;
  private String text;
  private Integer value;
  private Double cost;
  private Boolean active;
  private Date created;

  public Row(final List<Object> list) throws ParseException {
    rowNumber = Integer.parseInt(list.get(0).toString());
    text = list.get(1).toString();
    value = Integer.parseInt(list.get(2).toString());
    cost = Double.parseDouble(list.get(3).toString());
    active = Boolean.parseBoolean(list.get(4).toString());
    created = formatter.parse(list.get(5).toString());
  }
}
