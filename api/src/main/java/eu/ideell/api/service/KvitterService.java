package eu.ideell.api.service;

import eu.ideell.api.model.KvitterAccount;

public interface KvitterService {

  long createAccount(KvitterAccount resource);

  KvitterAccount getAccount();

}
