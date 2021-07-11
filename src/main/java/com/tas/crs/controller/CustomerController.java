package com.tas.crs.controller;

import com.tas.crs.entity.Customer;
import com.tas.crs.exception.CustomerNotFoundException;
import com.tas.crs.service.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    private final CustomerServiceImpl mCustomerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        mCustomerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> createAccount(final @RequestBody Customer customer) {
        return new ResponseEntity<>(mCustomerService.addCustomer(customer), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(mCustomerService.fetchCustomers(), OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomer(final @PathVariable(name = "id") Long customerId){
        Customer customer = mCustomerService
                .fetchCustomer(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID: %s not found", customerId)
                ));
        return ResponseEntity.badRequest().body(customer);
    }
}
