package com.tas.crs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tas.crs.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/accounts")
public class AccountController {

    // TODO: implement all account's methods
    private final AccountServiceImpl mAccountService;
    
    public AccountController(AccountService Impl accountService) {
        mAccountService = accountService;
    }
      
    @GetMapping
    public ResponseEntity<List<<Account>> getAllAccounts() {
        return new ResponseEntity<>(mAccountService.fetchAccounts(), OK);
    }
    
    @GetMapping(path ="/{id}")
    public ResponseEntity<Account> getAccount(final @PathVariable(name = "id") Long accountId) {
        Account account = mAccountService
            .fetchAccount(accountId);
        return ResponseEntity.badRequest().body(account);
    }
}
