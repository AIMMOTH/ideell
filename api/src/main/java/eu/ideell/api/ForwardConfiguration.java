package eu.ideell.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ForwardConfiguration implements WebMvcConfigurer {

  @Override
  public void addViewControllers(final ViewControllerRegistry registry) {
    registry.addViewController("/vi/").setViewName("forward:/vi/index.html");
    registry.addViewController("/konst/").setViewName("forward:/konst/index.html");
    registry.addViewController("/kvitter/").setViewName("forward:/kvitter/index.html");
  }
}
