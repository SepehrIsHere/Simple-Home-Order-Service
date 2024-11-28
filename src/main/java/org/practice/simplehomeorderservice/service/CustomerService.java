package org.practice.simplehomeorderservice.service;



import org.practice.simplehomeorderservice.dto.CustomerDto;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;

import java.util.List;

public interface CustomerService {

    Customer add(Customer customer) throws CustomerOperationException;

    void update(Customer customer) throws CustomerOperationException;

    void delete(Customer customer) throws CustomerOperationException;

    List<Customer> findAll() throws CustomerOperationException;

    Customer findById(int id) throws CustomerOperationException;

    Customer findByFirstNameAndLastName(String firstName,String lastName) throws CustomerOperationException;

    CustomerDto createCustomer(CustomerDto customerDto) throws CustomerOperationException;

    double getCustomerCredit(String customerFirstName,String customerLastName) throws CustomerOperationException;
}
