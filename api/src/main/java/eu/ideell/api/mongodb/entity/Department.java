package eu.ideell.api.mongodb.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document
@NoArgsConstructor
public class Department {

  @Id
  private String departmentName;
  @NonNull
  private String customerName;
  @Indexed
  private final Date created = new Date();
  private String name;
  private boolean active;

  public Department(final String customerName, final String departmentName) {
    this.customerName = customerName;
    this.departmentName = departmentName;
  }

}
