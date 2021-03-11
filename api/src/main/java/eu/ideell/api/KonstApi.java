package eu.ideell.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import eu.ideell.api.model.KvitterAccount;
import eu.ideell.api.service.KvitterService;

@Controller("kvitter")
public class KonstApi {

  @Autowired
  private KvitterService service;

  @PostMapping("account")
  public long postAccount(final KvitterAccount resource) {
    return service.createAccount(resource);
  }

  @GetMapping("me")
  public KvitterAccount getMe() {
    return service.getAccount();
  }

}
