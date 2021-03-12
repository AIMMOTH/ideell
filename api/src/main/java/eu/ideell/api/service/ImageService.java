package eu.ideell.api.service;

public interface ImageService {

  long createImage(byte[] image);

  byte[] readImage(long imageId, int width, int height);

}
