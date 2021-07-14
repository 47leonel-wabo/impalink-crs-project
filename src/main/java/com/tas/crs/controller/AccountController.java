package com.tas.crs.controller;

import com.tas.crs.entity.Account;
import com.tas.crs.service.AccountService;
import com.tas.crs.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;
import javax.security.auth.login.AccountNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.ResponseEntity.badRequest;

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
        Optional<Account> account = mAccountService
                .fetchAccount(accountId)
                .orElseThrow(() -> new AccountNotFoundException(
                        String.format("Account with ID: %s not found", accountId)
                ));
        return ResponseEntity.badRequest().body(account);
    }

}
