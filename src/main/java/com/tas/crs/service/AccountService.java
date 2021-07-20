package com.tas.crs.service;

import com.tas.crs.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {

    Account addAccount(Account account);

    List<Account> fetchAccounts();

    List<Account> fetchValidAccounts();

    List<Account> fetchClosedAccounts();

    Account updateAccountDetails(Account account);

    void deleteAccount(Long id) throws Exception;

    Optional<Account> fetchAccount(Long id);
}
