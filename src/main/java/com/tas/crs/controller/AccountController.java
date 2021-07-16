package com.tas.crs.controller;

import com.tas.crs.entity.Account;
import com.tas.crs.exception.AccountNotFoundException;
import com.tas.crs.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {

    // TODO: implement all account's methods
    private final AccountServiceImpl mAccountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        mAccountService = accountService;
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(mAccountService.fetchAccounts(), OK);
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<Account> getAccount(final @PathVariable(name = "id") Long accountId) {
        Account account = mAccountService.fetchAccount(accountId).orElseThrow(() -> new AccountNotFoundException("message here"));
        return ResponseEntity.badRequest().body(account);
    }

}
