package eu.ideell.api.model;

import java.net.URL;
import java.util.Date;

import lombok.Data;

@Data
public class IdeellAccount {

  private String auth0Subject;
  private String name;
  private String familyName;
  private Date dateOfBirth;
  private URL profilePhotoUrl;
  private String countryCode;
  private String languageCode;
}
