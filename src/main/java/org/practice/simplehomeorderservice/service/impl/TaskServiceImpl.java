package org.practice.simplehomeorderservice.service.impl;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.entities.Task;
import org.practice.simplehomeorderservice.exception.InvalidFieldValueException;
import org.practice.simplehomeorderservice.exception.TaskOperationException;
import org.practice.simplehomeorderservice.repository.TaskRepository;
import org.practice.simplehomeorderservice.service.TaskService;
import org.practice.simplehomeorderservice.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final ValidationUtil validationUtil;

    @Override
    public void add(Task task) throws TaskOperationException {
        try {
            if (validationUtil.isValid(task)) {
                taskRepository.save(task);
            } else {
                throw new InvalidFieldValueException("Some fields of task are not valid");
            }
        } catch (Exception e) {
            throw new TaskOperationException("An error occurred while adding a task", e);
        }
    }

    @Override
    public void update(Task task) throws TaskOperationException {
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new TaskOperationException("An error occurred while updating a task", e);
        }
    }

    @Override
    public void delete(Task task) throws TaskOperationException {
        try {
            taskRepository.delete(task);
        } catch (ConstraintViolationException e) {
            throw new TaskOperationException("Error while deleting task", e);
        }
    }

    @Override
    public List<Task> findAll() throws TaskOperationException {
        try {
            return taskRepository.findAll();
        } catch (Exception e) {
            throw new TaskOperationException("An error occurred while finding all tasks", e);
        }
    }

    @Override
    public Task findById(int id) throws TaskOperationException {
        try {
            return taskRepository.findByTaskId(id);
        } catch (Exception e) {
            throw new TaskOperationException("An error occurred while finding a task", e);
        }
    }

    @Override
    public Task findByName(String name) throws TaskOperationException {
        try {
            return taskRepository.findByTaskName(name);
        } catch (Exception e) {
            throw new TaskOperationException("An error occurred while finding a task", e);
        }
    }

    @Override
    public void changeTaskName(String taskName, String newName) throws TaskOperationException {
        try{
            Task task = findByName(taskName);
            task.setTaskName(newName);
            update(task);
        }catch (Exception e){
            throw new TaskOperationException("An error occurred while changing a task name", e);
        }
    }

    @Override
    public void changeTaskDescription(String taskName, String newDescription) throws TaskOperationException {
        try{
            Task task = findByName(taskName);
            task.setTaskDescription(newDescription);
            update(task);
        }catch (Exception e){
            throw new TaskOperationException("An error occurred while changing a task description", e);
        }
    }

    @Override
    public void deleteAll() throws TaskOperationException {
        taskRepository.deleteAll();
    }
}
