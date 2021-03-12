package eu.ideell.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import eu.ideell.api.service.ImageService;

@Controller("image")
public class ImageApi {

  @Autowired
  private ImageService service;

  @PostMapping("upload")
  public long postImage(@RequestBody final byte[] image) {
    return service.createImage(image);
  }

  @GetMapping("serve")
  public byte[] getImage(final long imageId, @RequestParam("width") final int width, @RequestParam("height") final int height) {
    return service.readImage(imageId, width, height);
  }
}
