package eu.ideell.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.model.KonstAccount;
import eu.ideell.api.service.KonstService;

@Controller("konst")
public class KvitterApi {

  @Autowired
  private KonstService service;

  @PostMapping("account")
  public long postAccount(final KonstAccount resource) {
    return service.createAccount(resource);
  }

  @GetMapping("me")
  public KonstAccount getMe() {
    return service.getAccount();
  }

}
