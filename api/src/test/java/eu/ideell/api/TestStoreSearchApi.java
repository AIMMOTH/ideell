package eu.ideell.api;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import eu.ideell.api.jersey.TestJersey;
import eu.ideell.api.service.model.StoreSearchRequest;

public class TestStoreSearchApi extends TestJersey {

  @Test
  public void test() {
    // Given
    final StoreSearchRequest request = new StoreSearchRequest("query");

    // When
    final List<Long> result = postUnauthenticated("public/search", request, List.class);

    // Then
    Assert.assertNotNull(result);
    Assert.assertTrue(result.isEmpty());
  }
}
