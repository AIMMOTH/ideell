package eu.ideell.api.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@CrossOrigin("${cors}")
//@EnableCaching
@SpringBootApplication
public class IdeellApplication {

  public static void main(final String[] args) {
      SpringApplication.run(IdeellApplication.class, args);
  }

}
