package eu.ideell.api.service.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class StoreSearchResponse {

  private List<ProductResource> products;
}
