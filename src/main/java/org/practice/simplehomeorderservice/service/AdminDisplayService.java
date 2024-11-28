package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.Users;
import org.practice.simplehomeorderservice.enumerations.Role;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.UserOperationException;

import java.util.List;

public interface AdminDisplayService {

    List<Customer> searchCustomers(String firstName, String lastName, String email, String username, double credit, String orderBy, boolean ascending);

    List<Specialist> searchSpecialists(String firstName, String lastName, String email, String username, double score, String subTaskName, String orderBy, boolean ascending);

    List<Customer> displayByCreditASC() throws CustomerOperationException;

    List<Customer> displayByCreditDESC() throws CustomerOperationException;

    List<Users> findByRole(Role role) throws UserOperationException;

    List<Users> findAllUserByEmailAsc() throws UserOperationException;

    List<Users> findAllUserByEmailDesc() throws UserOperationException;

    List<Users> findAllUserByFirstNameAsc() throws UserOperationException;

    List<Users> findAllUserByLastNameAsc() throws UserOperationException;

    List<Specialist> displayByScoreASC() throws SpecialistOperationException;

    List<Specialist> displayByScoreDESC() throws SpecialistOperationException;


}
