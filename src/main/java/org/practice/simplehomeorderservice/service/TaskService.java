package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.entities.Task;
import org.practice.simplehomeorderservice.exception.TaskOperationException;

import java.util.List;

public interface TaskService {
    void add(Task task) throws TaskOperationException;

    void update(Task task) throws TaskOperationException;

    void delete(Task task) throws TaskOperationException;

    void deleteAll() throws TaskOperationException;

    List<Task> findAll() throws TaskOperationException;

    Task findById(int id) throws TaskOperationException;

    Task findByName(String name) throws TaskOperationException;

    void changeTaskName(String taskName, String newName) throws TaskOperationException;

    void changeTaskDescription(String taskName, String newDescription) throws TaskOperationException;
}
