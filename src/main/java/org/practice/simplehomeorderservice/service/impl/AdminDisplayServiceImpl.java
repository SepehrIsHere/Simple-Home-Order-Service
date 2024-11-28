package org.practice.simplehomeorderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.Users;
import org.practice.simplehomeorderservice.enumerations.Role;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;
import org.practice.simplehomeorderservice.exception.NotFoundByFilterException;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.UserOperationException;
import org.practice.simplehomeorderservice.repository.CustomerRepository;
import org.practice.simplehomeorderservice.repository.SpecialistRepository;
import org.practice.simplehomeorderservice.repository.UsersRepository;
import org.practice.simplehomeorderservice.service.AdminDisplayService;
import org.practice.simplehomeorderservice.specification.CustomerSpecification;
import org.practice.simplehomeorderservice.specification.SpecialistSpecification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminDisplayServiceImpl implements AdminDisplayService {
    private final UsersRepository usersRepository;
    private final CustomerRepository customerRepository;
    private final SpecialistRepository specialistRepository;


    @Override
    public List<Customer> searchCustomers(String firstName, String lastName, String email, String username, double credit, String orderBy, boolean ascending) {
        try {
            CustomerSpecification specification = new CustomerSpecification(firstName, lastName, email, username, credit, orderBy, ascending);
            return customerRepository.findAll(specification);
        } catch (Exception e) {
            throw new NotFoundByFilterException("Customer not found using custom filters ");
        }
    }

    @Override
    public List<Specialist> searchSpecialists(String firstName, String lastName, String email, String username, double score, String subTaskName, String orderBy, boolean ascending) {
        try {
            SpecialistSpecification specification = new SpecialistSpecification(firstName, lastName, email, username, score, subTaskName, orderBy, ascending);
            return specialistRepository.findAll(specification);
        } catch (Exception e) {
            throw new NotFoundByFilterException("Special ist not found using custom filters ");
        }
    }

    @Override
    public List<Customer> displayByCreditASC() throws CustomerOperationException {
        try {
            return customerRepository.displayByCreditASC();
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while listing customer by credit ASC", e);
        }
    }

    @Override
    public List<Customer> displayByCreditDESC() throws CustomerOperationException {
        try {
            return customerRepository.displayByCreditDESC();
        } catch (Exception e) {
            throw new CustomerOperationException("An error occured while listing customer by credit DESC", e);
        }
    }

    @Override
    public List<Users> findByRole(Role role) throws UserOperationException {
        try {
            return usersRepository.findByRole(role);
        } catch (Exception e) {
            throw new UserOperationException("An error occured while listing user by role", e);
        }
    }

    @Override
    public List<Users> findAllUserByEmailAsc() throws UserOperationException {
        try {
            return usersRepository.findAllOrderByEmailAsc();
        } catch (Exception e) {
            throw new UserOperationException("An error occured while listing user by email ASC", e);
        }
    }

    @Override
    public List<Users> findAllUserByEmailDesc() throws UserOperationException {
        try {
            return usersRepository.findAllOrderByEmailDesc();
        } catch (Exception e) {
            throw new UserOperationException("An error occured while listing user by email DESC", e);
        }
    }

    @Override
    public List<Users> findAllUserByFirstNameAsc() throws UserOperationException {
        try {
            return usersRepository.findAllOrderByFirstNameAsc();
        } catch (Exception e) {
            throw new UserOperationException("An error occured while listing user by firstname ASC", e);
        }
    }

    @Override
    public List<Users> findAllUserByLastNameAsc() throws UserOperationException {
        try {
            return usersRepository.findAllOrderByLastNameAsc();
        } catch (Exception e) {
            throw new UserOperationException("An error occured while listing user by lastname ASC", e);
        }
    }

    @Override
    public List<Specialist> displayByScoreASC() throws SpecialistOperationException {
        try {
            return specialistRepository.displayByCreditASC();
        } catch (Exception e) {
            throw new SpecialistOperationException("An error occured while listing specialist by credit ASC", e);
        }
    }

    @Override
    public List<Specialist> displayByScoreDESC() throws SpecialistOperationException {
        try {
            return specialistRepository.displayByCreditDESC();
        } catch (Exception e) {
            throw new SpecialistOperationException("An error occured while listing specialist by credit DESC", e);
        }
    }
}
