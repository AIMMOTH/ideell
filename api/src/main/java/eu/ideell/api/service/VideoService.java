package eu.ideell.api.service;

public interface VideoService {

  public enum ResolutionType {
    hd, low
    ;
  }
  public enum QualityType {
    high, low
    ;
  }
  public long createVideo(byte[] video);

  public byte[] readVideo(long videoId, ResolutionType resolution, QualityType quality);
}
