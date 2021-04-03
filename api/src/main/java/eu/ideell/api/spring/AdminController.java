package eu.ideell.api.spring;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eu.ideell.api.mongodb.entity.Admin;

@Deprecated
@RestController
@RequestMapping("/admin")
public class AdminController {

  private Logger log = LoggerFactory.getLogger(getClass());
  @Autowired
  private AdminRepository repository;

  @GetMapping("")
  public List<Admin> getAll() {
    log.debug("Getting all admins ...");
    return repository.findAll();
  }

  @GetMapping("/{userName}")
  public Optional<Admin> getAdmin(@PathVariable final String userName) {
    return repository.findById(userName);
  }

//  @PostMapping("/create")
//  @PreAuthorize("hasAuthority('create:items')")
//  public Admin createAdmin(@RequestBody final Admin admin) {
//    return repository.save(admin);
//  }
}
