package eu.euphoros.api.service.model;

import java.util.Date;

import com.googlecode.objectify.Key;

import eu.euphoros.api.datastore.entity.DepartmentParent;
import eu.euphoros.api.datastore.entity.User;
import lombok.Data;
import se.cewebab.stockholm.bigquery.Tools;

@Data
public class BigQueryProduct {

  private final String exportedVersionCustomerDepartmentProduct;
  private final String exported = Tools.formatter.format(new Date());
  private final String version = "v1";

  private String customerName;
  private String departmentName;
  private String creatorUserName;
  private Long productId;
  private String created;
  private String name;
  private Double cost;
  private String description;
  private String category;
  private String imageUrl;

  public BigQueryProduct(final User user, final Product entity) {
    productId = entity.getProductId();

    final Key<DepartmentParent> parent = user.getUserParent().getParent();
    departmentName = parent.getName();
    customerName = parent.getParent().getName();
    creatorUserName = user.getUserName();

    created = Tools.formatter.format(entity.getCreated());
    name = entity.getName();
    cost = entity.getCost();
    description = entity.getDescription().getValue();
    category = entity.getCategory().name();
    imageUrl = entity.getImageUrl();

    exportedVersionCustomerDepartmentProduct = exported + version + customerName + departmentName + productId;
  }

}
