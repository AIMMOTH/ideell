package eu.ideell.api.datastore.entity;

import java.util.Date;

import com.googlecode.objectify.annotation.Cache;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import lombok.Data;

@Cache
@Data
@Entity
public class AdminInvitation {

  @Id
  private String email;
  @Index
  private Date created = new Date();
}
