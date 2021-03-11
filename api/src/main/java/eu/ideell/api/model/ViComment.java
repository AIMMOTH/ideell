package eu.ideell.api.model;

import java.net.URL;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ViComment implements CommentI {

  public enum CommentPurposeType {
    discussion, informative, sarcasm, joke
    ;

  }
  private CommentPurposeType purpose;
  private Date posted = new Date();
  private String textMax280Characters;
  private List<URL> imageUrls;
  private List<URL> links;
  private List<URL> videos;
  private String countryCode;
  private String languageCode;
  private boolean containsNudity;
  private boolean containsBlood;
  private boolean containsGraphicContent;

}
