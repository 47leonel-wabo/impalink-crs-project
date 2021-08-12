package com.tas.crs.controller;

import com.tas.crs.dto.CustomerDto;
import com.tas.crs.dto.EmailDto;
import com.tas.crs.entity.Account;
import com.tas.crs.entity.Customer;
import com.tas.crs.exception.CustomerNotFoundException;
import com.tas.crs.service.AccountServiceImpl;
import com.tas.crs.service.CustomerService;
import com.tas.crs.service.CustomerServiceImpl;
import com.tas.crs.service.email.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "/api/v1/customers")
public class CustomerController {

    @Autowired
    private ModelMapper modelMapper;

    private final CustomerServiceImpl mCustomerService;
    private final AccountServiceImpl accountService;
    private final EmailService mEmailService;

    @Autowired
    public CustomerController(CustomerServiceImpl mCustomerService, AccountServiceImpl accountService, EmailService mEmailService) {
        this.mCustomerService = mCustomerService;
        this.accountService = accountService;
        this.mEmailService = mEmailService;
    }

    @PostMapping
    public ResponseEntity<Customer> createAccount(final @RequestBody Customer customer) {
        return new ResponseEntity<>(mCustomerService.addCustomer(customer), CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(mCustomerService.fetchCustomers(), OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Customer> getCustomer(final @PathVariable(name = "id") Long customerId) {
        Customer customer = mCustomerService
                .fetchCustomer(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(
                        String.format("Customer with ID: %s not found", customerId)
                ));
        return ResponseEntity.badRequest().body(customer);
    }

    @PutMapping(path = "{/id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto customerDto) {

        //convert DTO to Entity
        Customer postCustomer = modelMapper.map(customerDto, Customer.class);

        Customer customer = mCustomerService.updateCustomerInfo(id, postCustomer);

        //convert entity to DTO
        CustomerDto customerResponse = modelMapper.map(customer, CustomerDto.class);

        return ResponseEntity.ok().body(customerResponse);
    }

    /*
    @PatchMapping(path = "{/id}")
    public ResponseEntity<Customer> updatedCustomer(@PathVariable(name = "id") Long id, @RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(mCustomerService.updateCustomerInfo(id, customerDto), OK);
    }

     */


    /*
    @PutMapping(path = "{/id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer newCustomer) {
        return mCustomerService.fetchCustomer(id).map(customer -> {
            customer.setEmail(newCustomer.getEmail());
            customer.setPhone(newCustomer.getPhone());
            customer.setTown(newCustomer.getTown());
            customer.setCity(newCustomer.getTown());
            return ResponseEntity.ok(customer);
        }).orElseThrow(() -> new CustomerNotFoundException("Customer not found"));
    }

     */

    @DeleteMapping(path = {"/account/{customer_id}"})
    public ResponseEntity<?> deleteAccount(final @PathVariable("customer_id") Long id) {
        // first get targeted customer
        Optional<Customer> customer = this.mCustomerService.fetchCustomer(id);
        if (customer.isEmpty()) {
            // throw an exception if there is no customer
            throw new CustomerNotFoundException(String.format("Customer with ID %s not found", id));
        }
        // if there is a customer, get its account by getting account linked to him
        Optional<Account> account = this.accountService.fetchAccount(customer.get().getAccount().getId());
        // then call delete method
        this.accountService.deleteAccount(account.get().getId());

        return ResponseEntity.ok().build();
    }

    @PostMapping(path = {"/email"})
    public ResponseEntity<?> sendEmailNotification(final @RequestBody EmailDto emailDto) {
        this.mEmailService.send(
                emailDto.getDestination(),
//                emailDto.getMessageContent()
                "<html>" +
                        "<head>" +
                        "<title>Customer Registration</title>" +
                        "<style>" +
                        "h1{background-color: #d6d2b2; color: white;}" +
                        "body{padding: 10px;}" +
                        "</style>" +
                        "</head>" +
                        "<body>" +
                        "<h1>Confirm Registration</h1>" +
                        "Dear, valuable customer<br/><br/>" +
                        "Welcome,<br/> please fill the form below to end your registration process<br/><br/>" +
                        "<div style=\"display: \"flex\"; flex-direction: \"column\"; justify-content: \"center\"; \">" +
                        "<form id=\"signup\">" +
                        "<di><label for=\"first_name\">First name</label>" +
                        "<br />" +
                        "<input type=\"text\" id=\"first_name\" />" +
                        "<small></small" +
                        "</div>" +
                        "<di><label for=\"last_name\">Last name</label>" +
                        "<br />" +
                        "<input type=\"text\" id=\"last_name\" />" +
                        "<small></small" +
                        "</div>" +
                        "<br />" +
                        "<input type=\"submit\" />" +
                        "</form>" +
                        "</div>" +
                        "<br />" +
                        "<br />" +
                        "<a href=\"http://localhost:8080/api/v1/customers/confirm?token=yes\">Activation Link</a><br/><br/>" +
                        "This link will expires in 15 min." +
                        "</body>" +
                        "</html>");
        return ResponseEntity.ok(String.format("Email sent to %s", emailDto.getDestination()));
    }
}
