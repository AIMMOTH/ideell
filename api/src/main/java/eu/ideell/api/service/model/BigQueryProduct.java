package eu.ideell.api.service.model;

import java.util.Date;

import eu.ideell.api.mongodb.entity.Product;
import eu.ideell.api.mongodb.entity.User;
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

    departmentName = entity.getDepartmentName();
    customerName = user.getCustomerName();
    creatorUserName = user.getUserName();

    created = Tools.formatter.format(entity.getCreated());
    name = entity.getName();
    cost = entity.getCost();
    description = entity.getDescription();
    category = entity.getCategory().name();
    imageUrl = entity.getImageUrl();

    exportedVersionCustomerDepartmentProduct = exported + version + customerName + departmentName + productId;
  }

}
