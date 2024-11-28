package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.SubTaskOperationException;
import org.practice.simplehomeorderservice.exception.TaskOperationException;

import java.time.LocalDate;
import java.util.List;

public interface OrderDisplayService {
    List<Order> getCustomerHistory(String customerFirstName, String customerLastName) throws CustomerOperationException;

    List<Order> getSpecialistHistory(String specialistFirstName, String specialistLastName) throws SpecialistOperationException;

    List<Order> getDateHistory(LocalDate startDate, LocalDate endDate);

    List<Order> getSubTaskHistory(String subTaskName) throws SubTaskOperationException;

    List<Order> getTaskHistory(String taskName) throws TaskOperationException;

    List<Order> getOrderStatusHistory(OrderStatus orderStatus);
}
