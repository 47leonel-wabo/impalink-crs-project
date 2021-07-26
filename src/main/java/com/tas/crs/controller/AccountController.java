package com.tas.crs.controller;

import com.tas.crs.entity.Account;
import com.tas.crs.entity.Customer;
import com.tas.crs.exception.AccountNotFoundException;
import com.tas.crs.service.AccountServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api")
public class AccountController {

    // TODO: implement account methods
    private final AccountServiceImpl mAccountService;

    @Autowired
    public AccountController(AccountServiceImpl accountService) {
        mAccountService = accountService;
    }

    @PostMapping(path = "/v1/accounts")
    @ApiOperation(value = "Creates a new account",
            notes = "Fill in the form with required info",
            response = Account.class)
    public ResponseEntity<Account> createAccount(final @RequestBody Account account) {
        return new ResponseEntity<>(mAccountService.addAccount(account), OK);
    }

    @GetMapping(path = "/v1/accounts")
    @ApiOperation(value = "Retrieves all accounts",
            notes = "Provides a ist of existing accounts",
            response = Account.class)
    public ResponseEntity<List<Account>> getAllAccounts() {
        return new ResponseEntity<>(mAccountService.fetchAccounts(), OK);
    }

    @GetMapping(path ="/v1/accounts/{id}")
    @ApiOperation(value = "Finds accounts by id",
            notes = "Provide an id to look up a specific account from accounts list",
            response = Account.class)
    public ResponseEntity<Account> getAccount(final @PathVariable(name = "id") Long accountId) {
        Account account = mAccountService.fetchAccount(accountId).orElseThrow(() -> new AccountNotFoundException("message here"));
        return ResponseEntity.badRequest().body(account);
    }

    @PutMapping(path = "/v1/accounts/{id}")
    @ApiOperation(value = "Updates accounts by id",
            notes = "Provide an id to modify a specific account's details from accounts list",
            response = Account.class)
    public ResponseEntity<Account> updateAccount(@RequestBody Account account, @PathVariable(name = "id") Long accountId) {
        return new ResponseEntity<Account>(mAccountService.updateAccountDetails(account), OK);
    }

//    @DeleteMapping(path = "/{id}")
//    public void deleteAccount(final @PathVariable(name = "id") Long accountId) throws Exception {
//
//        mAccountService.deleteAccount(accountId);
//    }
}
