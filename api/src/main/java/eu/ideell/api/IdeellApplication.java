package eu.ideell.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

/*
 * Same as adding
 * @Configuration
 * @EnableAutoConfiguration
 * @ComponentScan
 */
@SpringBootApplication
@CrossOrigin("*")
//@CrossOrigin("${cors}")
@EnableCaching // https://spring.io/guides/gs/caching/
public class IdeellApplication {

  public static void main(final String[] args) {
      SpringApplication.run(IdeellApplication.class, args);
  }

}
