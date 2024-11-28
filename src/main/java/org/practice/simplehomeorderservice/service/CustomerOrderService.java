package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;
import org.practice.simplehomeorderservice.exception.OrderOperationException;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;

import java.util.List;

public interface CustomerOrderService {
    OrderDto placeAnOrder(OrderDto orderDto) throws OrderOperationException;

    void pickSpecialistForOrder(String specialistFirstName,String specialistLastName,String nameOfOrder) throws OrderOperationException;

    List<OrderDto> findCustomersOrders(String customerFirstName, String customerLastName) throws CustomerOperationException;

    void changeOrderStatusToStarted(String nameOfOrder,String specialistFirstName,String specialistLastName) throws OrderOperationException, SuggestionOperationException;

    void changeOrderStatusToFinished(String nameOfOrder) throws OrderOperationException;
}
