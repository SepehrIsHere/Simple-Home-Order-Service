package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.CustomerDto;
import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.dto.SubTaskDto;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.exception.OrderOperationException;

import java.util.List;

public interface OrderService {

    void add(Order order) throws OrderOperationException;

    void update(Order order) throws OrderOperationException;

    void delete(Order order) throws OrderOperationException;

    List<Order> findAll() throws OrderOperationException;

    Order findByNameOfOrder(String nameOfOrder);

    List<OrderDto> findByRelatedSubTask(SubTaskDto subTaskDto) throws OrderOperationException;

    List<Order> findWaitingForSelectionOrders() throws OrderOperationException;

    List<OrderDto> findCustomerOrders(CustomerDto customerDto);

}
