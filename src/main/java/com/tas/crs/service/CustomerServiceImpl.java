package com.tas.crs.service;

import com.tas.crs.entity.Account;
import com.tas.crs.entity.Customer;
import com.tas.crs.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository mCustomerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        mCustomerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(final Customer customer) {
        // Each register customer get account
        customer.setAccount(new Account());
        return getSave(customer);
    }

    @Override
    public void deleteCustomer(final Long customerId) {
        mCustomerRepository.deleteById(customerId);
    }

    @Override
    public Customer updateCustomerInfo(final Customer customer) {
        return getSave(customer);
    }

    @Override
    public List<Customer> fetchCustomers() {
        return mCustomerRepository.findAll();
    }

    @Override
    public Optional<Customer> fetchCustomer(final Long customerId) {
        return mCustomerRepository.findById(customerId);
    }

    private Customer getSave(Customer customer) {
        return mCustomerRepository.save(customer);
    }
}
