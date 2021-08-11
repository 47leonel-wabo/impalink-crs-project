package com.tas.crs.service;

import com.tas.crs.dto.CustomerDto;
import com.tas.crs.entity.Account;
import com.tas.crs.entity.Customer;
import com.tas.crs.exception.CustomerNotFoundException;
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
        Optional<Customer> optionalCustomer = fetchCustomer(customerId);
        if (optionalCustomer.isPresent()) {
            mCustomerRepository.deleteById(customerId);
        } else {
            throw new CustomerNotFoundException(String.format("Customer with ID: %s not found", customerId));
        }
    }


    @Override
    public Customer updateCustomerInfo(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomerInfo(Long id, CustomerDto customerDto) {
        if(mCustomerRepository.findById(id).isPresent()) {
            Customer existingCustomer = mCustomerRepository.findById(id).get();

            existingCustomer.setEmail(customerDto.getEmail());
            existingCustomer.setPhone(customerDto.getEmail());
            existingCustomer.setTown(customerDto.getTown());
            existingCustomer.setCity(customerDto.getCity());

            return mCustomerRepository.save(existingCustomer);

            /*
            return new Customer(updatedCustomer.getId(),
                    updatedCustomer.getFirstName(),
                    updatedCustomer.getLastName(),
                    updatedCustomer.getEmail(),
                    updatedCustomer.getPhone(),
                    updatedCustomer.getGender(),
                    updatedCustomer.getTown(),
                    updatedCustomer.getCity()
                    );

             */
        }

        return null;
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
