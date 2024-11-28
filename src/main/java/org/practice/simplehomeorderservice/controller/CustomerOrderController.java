package org.practice.simplehomeorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.exception.OrderOperationException;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;
import org.practice.simplehomeorderservice.service.CustomerOrderService;
import org.practice.simplehomeorderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;
    private final OrderService orderService;

    @PostMapping("customer/order/create")
    OrderDto placeAnOrder(@RequestBody OrderDto orderDto) throws OrderOperationException {
        return customerOrderService.placeAnOrder(orderDto);
    }

    @PatchMapping("customer/order/pick/{nameOfOrder}/{specialistFirstName}/{specialistLastName}")
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<String> pickSpecialistForOrder(@PathVariable String nameOfOrder,@PathVariable String specialistFirstName,@PathVariable String specialistLastName) throws OrderOperationException {
        customerOrderService.pickSpecialistForOrder(specialistFirstName,specialistLastName,nameOfOrder);
        return ResponseEntity.ok("Order picked by " + specialistFirstName + " " + specialistLastName);
    }

    @PatchMapping("customer/order/started/{nameOfOrder}/{specialistFirstName}/{specialistLastName}")
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<String> changeOrderStatusToStarted(@PathVariable String nameOfOrder,@PathVariable String specialistFirstName,@PathVariable String specialistLastName) throws OrderOperationException, SuggestionOperationException {
        customerOrderService.changeOrderStatusToStarted(nameOfOrder,specialistFirstName,specialistLastName);
        return ResponseEntity.ok("Order status changed to started");
    }

    @PatchMapping("customer/order/finished/{nameOfOrder}")
    @PreAuthorize("hasRole('CUSTOMER')")
    ResponseEntity<String> changeOrderStatusToFinished(@PathVariable String nameOfOrder) throws OrderOperationException {
        customerOrderService.changeOrderStatusToFinished(nameOfOrder);
        return ResponseEntity.ok("Order Status changed to finished ");
    }
}
