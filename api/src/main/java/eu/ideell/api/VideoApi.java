package eu.ideell.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import eu.ideell.api.service.VideoService;

@Controller("video")
public class VideoApi {

  @Autowired
  private VideoService service;

  @PostMapping("upload")
  public long postVideo(@RequestBody final byte[] video) {
    return service.createVideo(video);
  }

  @GetMapping("serve")
  public byte[] getImage(final long videoId, @RequestParam("resolution") final VideoService.ResolutionType resolution, @RequestParam("quality") final VideoService.QualityType quality) {
    return service.readVideo(videoId, resolution, quality);
  }

}
