package eu.euphoros.api.jersey;

import java.io.IOException;

import org.mockito.Mockito;

import com.google.cloud.bigquery.BigQuery;
import com.google.cloud.bigquery.InsertAllResponse;

import se.cewebab.stockholm.bigquery.AbstractBigQueryIntegration;

public class TestBigQueryImpl extends AbstractBigQueryIntegration {

  @Override
  protected BigQuery createBigQuery() throws IOException {
    final InsertAllResponse response = Mockito.mock(InsertAllResponse.class);
    Mockito.when(response.hasErrors()).thenReturn(false);
    final BigQuery mock = Mockito.mock(BigQuery.class);
    Mockito.when(mock.insertAll(Mockito.any())).thenReturn(response);
    return mock;
  }

}
