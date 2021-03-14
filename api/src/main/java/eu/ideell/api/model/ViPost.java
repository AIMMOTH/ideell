package eu.ideell.api.model;

import java.net.URL;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class ViPost implements PostI {

  public enum PostPurposeType {
    informative, discussion, joke, sarcasm, ironic
    ;
  }
  private String ideellAccountId;
  private Long viAccountId;
  private PostPurposeType type;
  private Date posted = new Date();
  private String textMax280Characters;
  private List<URL> imageUrls;
  private List<URL> links;
  private List<URL> videos;
  private List<String> tagsWithoutWhitespace;
  private String countryCode;
  private String languageCode;
  private boolean containsNudity;
  private boolean containsBlood;
  private boolean containsGraphicContent;

}
