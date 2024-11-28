package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.dto.SpecialistDto;
import org.practice.simplehomeorderservice.exception.OrderOperationException;

import java.util.List;

public interface SpecialistOrderService {
    List<OrderDto> displayOrdersRelatedToSubTask(String specialistFirstName, String specialistLastName) throws  OrderOperationException;

    void pickOrder(OrderDto orderDto, SpecialistDto specialistDto) throws OrderOperationException;

    List<OrderDto> displayWaitingForSelectionOrders() throws  OrderOperationException;
}
