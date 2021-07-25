package com.tas.crs.controller;

import com.tas.crs.entity.Customer;
import com.tas.crs.exception.CustomerNotFoundException;
import com.tas.crs.service.CustomerServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Request confirmed"),
        @ApiResponse(code = 400, message = "This is a bad request, please follow the API documentation"),
        @ApiResponse(code = 401, message = "Due to security constraints, your access request cannot be authorised"),
        @ApiResponse(code = 500, message = "The server is down")
})
@RequestMapping(path = "/api")
public class CustomerController {

    private final CustomerServiceImpl mCustomerService;

    @Autowired
    public CustomerController(CustomerServiceImpl customerService) {
        mCustomerService = customerService;
    }

    @PostMapping(path = "/v1/customers")
    @ApiOperation(value = "Creates a new customer and automatically attributes an account to him",
            notes = "Fill in the form with required info",
            response = Customer.class)
    public ResponseEntity<Customer> createCustomer(final @RequestBody Customer customer) {
        return new ResponseEntity<>(mCustomerService.addCustomer(customer), CREATED);
    }

    @GetMapping(path = "/v1/customers")
    @ApiOperation(value = "Retrieves all customers",
            notes = "Provides a ist of existing customers",
            response = Customer.class)
    public ResponseEntity<List<Customer>> getCustomers(){
        return new ResponseEntity<>(mCustomerService.fetchCustomers(), OK);
    }

    @GetMapping(path = "/v1/customers/{id}")
    @ApiOperation(value = "Finds Customers by id",
            notes = "Provide an id to look up a specific customer from customer list",
            response = Customer.class)
    public ResponseEntity<Customer> getCustomer(final @PathVariable(name = "id") Long customerId){
        Customer customer = mCustomerService
                .fetchCustomer(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID: %s not found", customerId)
                ));
        return ResponseEntity.badRequest().body(customer);
    }

    @PutMapping(path = "/v1/customers/{id}")
    @ApiOperation(value = "Updates customers by id",
            notes = "Provide an id to modify a specific customer's details from customer list",
            response = Customer.class)
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @PathVariable(name = "id") Long customerId) {
        return new ResponseEntity<Customer>(mCustomerService.updateCustomerInfo(customer), OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteCustomer(final @PathVariable(name = "id") Long customerId){
        mCustomerService.deleteCustomer(customerId);
    }
}
