package org.practice.simplehomeorderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.dto.SpecialistDto;
import org.practice.simplehomeorderservice.dto.SubTaskDto;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.practice.simplehomeorderservice.exception.OrderNotFoundException;
import org.practice.simplehomeorderservice.exception.OrderOperationException;
import org.practice.simplehomeorderservice.exception.SubTaskNotFoundException;
import org.practice.simplehomeorderservice.service.SpecialistOrderService;
import org.practice.simplehomeorderservice.service.SpecialistService;
import org.practice.simplehomeorderservice.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistOrderServiceImpl implements SpecialistOrderService {
    private final SpecialistService specialistService;
    private final OrderServiceImpl orderService;
    private final MapperUtil mapperUtil;

    //Query-Database
    //performance
    @Override
    public List<OrderDto> displayOrdersRelatedToSubTask(String specialistFirstName, String specialistLastName) throws OrderOperationException {
        try {
            Specialist specialist = specialistService.findByFirstNameAndLastName(specialistFirstName, specialistLastName);

            //**
            List<SubTaskDto> subTasks = specialist.getSubTasks()
                    .stream()
                    .map(mapperUtil::convertToDto)
                    .toList();
            if (subTasks.isEmpty()) {
                throw new SubTaskNotFoundException("No related SubTasks found !");
            }
            //**
            List<OrderDto> relatedOrders = new ArrayList<>();
            for (SubTaskDto subTaskDto : subTasks) {
                relatedOrders = orderService.findByRelatedSubTask(subTaskDto);
            }
            return relatedOrders;
        } catch (Exception e) {
            throw new OrderOperationException("Failed to find related orders of subtask", e);
        }
    }

    @Override
    public void pickOrder(OrderDto orderDto, SpecialistDto specialistDto) throws OrderOperationException {
        try {
            Specialist specialist = specialistService.findByFirstNameAndLastName(specialistDto.getFirstname(),
                    specialistDto.getLastname());
            Order order = orderService.findByNameOfOrder(orderDto.getNameOfOrder());

            List<Order> specialistOrders = specialist.getOrders();
            specialistOrders.add(order);
            specialist.setOrders(specialistOrders);
            specialistService.update(specialist);

            order.setSpecialist(specialist);
            order.setStatus(OrderStatus.WAITING_FOR_SPECIALIST_ARRIVAL);
            orderService.update(order);
        } catch (Exception e) {
            throw new OrderOperationException("Failed to assign order to specialist", e);
        }
    }

    @Override
    public List<OrderDto> displayWaitingForSelectionOrders() throws OrderOperationException {
        try {
            List<OrderDto> orders = orderService.findWaitingForSelectionOrders()
                    .stream()
                    .map(mapperUtil::convertToDto)
                    .toList();
            if (orders != null && !orders.isEmpty()) {
                return orders;
            } else {
                throw new OrderNotFoundException("Orders Not Found ");
            }
        } catch (Exception e) {
            throw new OrderOperationException("Failed to display orders ", e);
        }
    }
}
