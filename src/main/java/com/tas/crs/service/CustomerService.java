package com.tas.crs.service;

import com.tas.crs.dto.CustomerDto;
import com.tas.crs.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer addCustomer(Customer customer);

    void deleteCustomer(Long customerId);

    Customer updateCustomerInfo(Long id, Customer customer);

    //Customer updateCustomerInfo(Long id, CustomerDto customerDto);

    List<Customer> fetchCustomers();

    Optional<Customer> fetchCustomer(Long customerId);
}
