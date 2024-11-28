package org.practice.simplehomeorderservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.entities.Users;
import org.practice.simplehomeorderservice.exception.UserOperationException;
import org.practice.simplehomeorderservice.repository.UsersRepository;
import org.practice.simplehomeorderservice.service.UsersService;
import org.practice.simplehomeorderservice.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {
    private final ValidationUtil validationUtil;
    private final UsersRepository usersRepository;

    @Override
    public void add(Users users) throws UserOperationException {
        try {
            if (validationUtil.isValid(users)) {
                usersRepository.save(users);
            } else {
                validationUtil.displayViolations(users);
            }
        } catch (Exception e) {
            throw new UserOperationException("An error occured while adding user", e);
        }
    }

    @Override
    public void update(Users users) throws UserOperationException {
        try {
            usersRepository.save(users);
        } catch (Exception e) {
            throw new UserOperationException("Error while updating user ", e);
        }
    }

    @Override
    public void delete(Users users) throws UserOperationException {
        try {
            usersRepository.delete(users);
        } catch (Exception e) {
            throw new UserOperationException("Error while deleting user ", e);
        }
    }

    @Override
    public List<Users> findAll() throws UserOperationException {
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            throw new UserOperationException("An error occured while finding user", e);
        }
    }

    @Override
    public Users findById(int id) throws UserOperationException {
        try {
            return usersRepository.findUserById(id);
        } catch (Exception e) {
            throw new UserOperationException("An error occured while finding user ", e);
        }
    }


}
