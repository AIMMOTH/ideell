package eu.ideell.api.spring;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexResource {

  @GetMapping("index2.html")
  public String get() {
    return "<html><head><title>Ideell - Idealistic Social Media</title></head><body><h1>Ideell</h1><p>Welcome, ...</p></body></html>";
  }
}
