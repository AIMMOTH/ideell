package eu.ideell.api.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.api.services.sheets.v4.model.ValueRange;
import com.google.common.collect.Lists;

import eu.ideell.api.service.model.Row;
import eu.ideell.api.service.model.SheetsResponse;
import se.cewebab.stockholm.sheets.Sheets;

public class StoreSheets {

  @Autowired
  private Sheets sheets;

  public SheetsResponse get() throws GeneralSecurityException, IOException, URISyntaxException, ParseException {
    final ValueRange valueRange = sheets.get("1Qn4oa5LkCCkNOWG1wdaTfdmbmp8UneIAXgm6oHqm1FM", "Data!A1:F3");
    final List<List<Object>> values = valueRange.getValues();
    final List<String> headers = values.get(0).stream().map(o -> String.valueOf(o)).collect(Collectors.toList());
    final ArrayList<Row> rows = Lists.newArrayList(new Row(values.get(1)), new Row(values.get(2)));
    return new SheetsResponse(headers, rows);
  }

}
