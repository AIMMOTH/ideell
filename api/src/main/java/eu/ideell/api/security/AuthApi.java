package eu.ideell.api.security;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class AuthApi {

  @GetMapping("/api/v1/public/")
  public String getPublic() {
    return "public pong";
  }

  @GetMapping("/api/v1/private/")
  public String getPrivate() {
    return "private pong";
  }
}
