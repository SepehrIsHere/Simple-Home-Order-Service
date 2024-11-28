package org.practice.simplehomeorderservice.service;



import org.practice.simplehomeorderservice.entities.Users;
import org.practice.simplehomeorderservice.exception.UserOperationException;

import java.util.List;

public interface UsersService {

    void add(Users users) throws UserOperationException;

    void update(Users users) throws UserOperationException;

    void delete(Users users) throws UserOperationException;

    List<Users> findAll() throws UserOperationException;

    Users findById(int id) throws UserOperationException;

}
