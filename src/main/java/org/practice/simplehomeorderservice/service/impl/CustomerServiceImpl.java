package org.practice.simplehomeorderservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.CustomerDto;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.enumerations.Role;
import org.practice.simplehomeorderservice.exception.CustomerNotFoundException;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;
import org.practice.simplehomeorderservice.repository.CustomerRepository;
import org.practice.simplehomeorderservice.service.CustomerService;
import org.practice.simplehomeorderservice.util.ValidationUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final ValidationUtil validationUtil;
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Customer add(Customer customer) throws CustomerOperationException {
        try {
            if (validationUtil.isValid(customer)) {
                return customerRepository.save(customer);
            } else {
                validationUtil.displayViolations(customer);
            }
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while adding customer", e);
        }
        return null;
    }

    @Override
    public void update(Customer customer) throws CustomerOperationException {
        try {
            if (customer != null) {
                customerRepository.save(customer);
            } else {
                throw new CustomerNotFoundException("Customer Not Found");
            }
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while updating customer", e);
        }
    }

    @Override
    public void delete(Customer customer) throws CustomerOperationException {
        try {
            customerRepository.delete(customer);
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while deleting customer", e);
        }
    }

    @Override
    public List<Customer> findAll() throws CustomerOperationException {
        try {
            return customerRepository.findAll();
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while finding all customers", e);
        }
    }

    @Override
    public Customer findById(int id) throws CustomerOperationException {
        try {
            return customerRepository.findCustomerById(id);
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while finding customer", e);
        }
    }

    //if customer valid --> add
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) throws CustomerOperationException {
        Customer customer = Customer.builder()
                .firstName(customerDto.getFirstname())
                .lastName(customerDto.getLastname())
                .username(customerDto.getUsername())
                .password(passwordEncoder.encode(customerDto.getPassword()))
                .email(customerDto.getEmail())
                .role(Role.CUSTOMER)
                .credit(0.0)
                .orders(new ArrayList<>())
                .comments(new ArrayList<>())
                .build();
        add(customer);
        return customerDto;
    }

    @Override
    public Customer findByFirstNameAndLastName(String firstName, String lastName) throws CustomerOperationException {
        try {
            Customer customer = customerRepository.findCustomerByFirstNameAndLastName(firstName, lastName);
            if (customer != null) {
                return customer;
            } else {
                throw new CustomerNotFoundException("Customer Not Found");
            }
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while finding customer", e);
        }
    }


    @Override
    public double getCustomerCredit(String customerFirstName, String customerLastName) throws CustomerOperationException {
        Customer customer = findByFirstNameAndLastName(customerFirstName, customerLastName);
        return customer.getCredit();
    }
}
