package com.tas.crs.service;

import com.tas.crs.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Customer updateCustomerInfo(Customer customer);

    List<Customer> fetchCustomers();

    Optional<Customer> fetchCustomer(Long customerId);
}
