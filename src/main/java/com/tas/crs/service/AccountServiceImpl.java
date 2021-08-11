package com.tas.crs.service;

import com.tas.crs.entity.Account;
import com.tas.crs.exception.AccountNotFoundException;
import com.tas.crs.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository mAccountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        mAccountRepository = accountRepository;
    }

    @Override
    public List<Account> fetchAccounts() {
        return mAccountRepository.findAll();
    }

    @Override
    public List<Account> fetchValidAccounts() {
        return mAccountRepository.findByClosedFalse();
    }

    @Override
    public List<Account> fetchClosedAccounts() {
        return mAccountRepository.findByClosedTrue();
    }

    @Override
    public void deleteAccount(final Long id) {
        Optional<Account> optionalAccount = mAccountRepository.findById(id);
        if (optionalAccount.isEmpty()) {
            throw new AccountNotFoundException(String.format("Account with ID: %s not found", id));
        }
        Account account = optionalAccount.get();
        account.setClosed(true);
        mAccountRepository.save(account);
    }

    @Override
    public Optional<Account> fetchAccount(final Long id) {
        return mAccountRepository.findById(id);
    }
}
