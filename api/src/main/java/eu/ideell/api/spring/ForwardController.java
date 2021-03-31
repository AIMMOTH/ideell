package eu.ideell.api.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ForwardController {

  @GetMapping(value = "/vi/**")
  public String forward() {
    return "forward:vi/";
  }
}
