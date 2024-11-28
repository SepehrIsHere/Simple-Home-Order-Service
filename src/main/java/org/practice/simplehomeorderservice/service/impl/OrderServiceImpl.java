package org.practice.simplehomeorderservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.CustomerDto;
import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.dto.SubTaskDto;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.SubTask;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.practice.simplehomeorderservice.exception.*;
import org.practice.simplehomeorderservice.repository.CustomerRepository;
import org.practice.simplehomeorderservice.repository.OrderRepository;
import org.practice.simplehomeorderservice.repository.SubTaskRepository;
import org.practice.simplehomeorderservice.service.OrderService;
import org.practice.simplehomeorderservice.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final MapperUtil mapperUtil;
    private final CustomerRepository customerRepository;
    private final SubTaskRepository subTaskRepository;
    private final OrderRepository orderRepository;

    @Override
    public Order findByNameOfOrder(String nameOfOrder) {
        Order order = orderRepository.findByNameOfOrder(nameOfOrder);
        if (order != null) {
            return order;
        } else {
            throw new OrderNotFoundException("Order Not Found");
        }
    }

    @Override
    public void add(Order order) throws OrderOperationException {
        try {
//            if (validationUtil.isValid(order)) {
                orderRepository.save(order);
//            } else {
//                validationUtil.displayViolations(order);
//            }
        } catch (Exception e) {
            throw new OrderOperationException("An error occurred while adding an order ", e);
        }
    }

    @Override
    public void update(Order order) throws OrderOperationException {
        try {
            orderRepository.save(order);
        } catch (Exception e) {
            throw new OrderOperationException("An error occurred while updating an order ", e);
        }
    }

    @Override
    public void delete(Order order) throws OrderOperationException {
        try {
            orderRepository.delete(order);
        } catch (Exception e) {
            throw new OrderOperationException("An error occurred while deleting an order ", e);
        }
    }

    @Override
    public List<Order> findAll() throws OrderOperationException {
        try {
            return orderRepository.findAll();
        } catch (Exception e) {
            throw new OrderOperationException("An error occurred while finding all orders ", e);
        }
    }

    @Override
    public List<OrderDto> findByRelatedSubTask(SubTaskDto subTaskDto) throws OrderOperationException {
        try {
            SubTask subTask = subTaskRepository.findByName(subTaskDto.getSubTaskName());
            if (subTask != null) {
                return orderRepository.findByRelatedSubTask(subTask)
                        .stream()
                        .map(mapperUtil::convertToDto)
                        .toList();
            } else {
                throw new SubTaskNotFoundException("SubTask Not Found");
            }
        } catch (Exception e) {
            throw new OrderOperationException("An error occured while finding related orders ", e);
        }
    }

    @Override
    public List<Order> findWaitingForSelectionOrders() throws OrderOperationException {
        try{
            return orderRepository.findOrderByOrderStatus(OrderStatus.WAITING_FOR_SPECIALIST_SELECTION);
        }catch (Exception e){
            throw new OrderOperationException("An error occured while finding related orders ",e);
        }
    }

    @Override
    public List<OrderDto> findCustomerOrders(CustomerDto customerDto) {
        Customer customer = customerRepository.findCustomerByFirstNameAndLastName(customerDto.getFirstname(), customerDto.getLastname());
        if (customer != null) {
            List<Order> customerOrders = orderRepository.findByCustomer(customer);
            if (customerOrders != null && !customerOrders.isEmpty()) {
                List<OrderDto> orderDtos = new ArrayList<>();
                for (Order order : customerOrders) {
                    orderDtos.add(mapperUtil.convertToDto(order));
                }
                return orderDtos;
            } else {
                throw new CustomerOrderListEmpty("Customer does not have any order ! ");
            }
        } else {
            throw new CustomerNotFoundException("Customer Not Found !");
        }
    }
}
