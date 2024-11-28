package org.practice.simplehomeorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.practice.simplehomeorderservice.exception.CustomerOperationException;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.SubTaskOperationException;
import org.practice.simplehomeorderservice.exception.TaskOperationException;
import org.practice.simplehomeorderservice.service.OrderDisplayService;
import org.practice.simplehomeorderservice.util.MapperUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderDisplayController {
    private final OrderDisplayService orderDisplayService;
    private final MapperUtil mapperUtil;

    @GetMapping("customer-history/{customerFirstName}/{customerLastName}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','CUSTOMER')")
    //Query parameter
    List<OrderDto> getCustomerHistory(@PathVariable("customerFirstName") String customerFirstName, @PathVariable("customerLastName") String customerLastName) throws CustomerOperationException {
        return orderDisplayService.getCustomerHistory(customerFirstName, customerLastName)
                .stream()
                .map(mapperUtil::convertToDto)
                .toList();
    }

    @GetMapping("specialist-history/{specialistFirstName}/{specialistLastName}")
    @PreAuthorize("hasAnyRole('ADMINISTRATOR','SPECIALIST')")
        //Query parameter
    List<OrderDto> getSpecialistHistory(@PathVariable("specialistFirstName") String specialistFirstName, @PathVariable("specialistLastName") String specialistLastName) throws SpecialistOperationException {
        return orderDisplayService.getSpecialistHistory(specialistFirstName, specialistLastName)
                .stream()
                .map(mapperUtil::convertToDto)
                .toList();
    }

    @GetMapping("task-history/{taskName}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    List<OrderDto> getTaskHistory(@PathVariable("taskName") String taskName) throws TaskOperationException {
        return orderDisplayService.getTaskHistory(taskName)
                .stream()
                .map(mapperUtil::convertToDto)
                .toList();
    }

    @GetMapping("subTask-history/{subTaskName}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    List<OrderDto> getSubTaskHistory(@PathVariable("subTaskName") String subTaskName) throws SubTaskOperationException {
        return orderDisplayService.getSubTaskHistory(subTaskName)
                .stream()
                .map(mapperUtil::convertToDto)
                .toList();
    }

    @GetMapping("orderStatus-history/{orderStatus}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    List<OrderDto> getOrderStatusHistory(@PathVariable("orderStatus") OrderStatus orderStatus) {
        return orderDisplayService.getOrderStatusHistory(orderStatus)
                .stream()
                .map(mapperUtil::convertToDto)
                .toList();
    }

    @GetMapping("history-order-by-date/{startDate}/{endDate}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    List<OrderDto> getOrderDateHistory(@PathVariable("startDate") LocalDate startDate, @PathVariable("endDate") LocalDate endDate) {
        return orderDisplayService.getDateHistory(startDate, endDate)
                .stream()
                .map(mapperUtil::convertToDto)
                .toList();
    }
}
