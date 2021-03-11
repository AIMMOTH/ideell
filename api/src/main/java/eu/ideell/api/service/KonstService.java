package eu.ideell.api.service;

import eu.ideell.api.model.KonstAccount;

public interface KonstService {

  long createAccount(KonstAccount resource);

  KonstAccount getAccount();

}
