package org.practice.simplehomeorderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.CreditCardDto;
import org.practice.simplehomeorderservice.dto.ReceiptDto;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.Suggestions;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;
import org.practice.simplehomeorderservice.enumerations.SpecialistStatus;
import org.practice.simplehomeorderservice.exception.*;
import org.practice.simplehomeorderservice.service.*;
import org.practice.simplehomeorderservice.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final CustomerService customerService;
    private final SpecialistService specialistService;
    private final SuggestionsService suggestionsService;
    private final ReceiptService receiptService;
    private final OrderService orderService;
    private final ValidationUtil validationUtil;

    @Override
    public void payWithCustomerCredit(String nameOfOrder) {
        try {
            ReceiptDto receiptDto = receiptService.findByNameOfOrder(nameOfOrder);
            Specialist specialist =
                    specialistService.findByFirstNameAndLastName(receiptDto.getSpecialistFirstName(),
                            receiptDto.getSpecialistLastName());
            Customer customer =
                    customerService.findByFirstNameAndLastName(receiptDto.getCustomerFirstName(),
                            receiptDto.getCustomerLastName());
            Order order =
                    orderService.findByNameOfOrder(receiptDto.getNameOfOrder());
            if (!canPayForOrder(receiptDto)) {
                throw new PaymentFailedException("Failed to pay using customer credit");
            }

            if (!(customer.getCredit() >= receiptDto.getTotalAmount() && customer.getCredit() != 0)) {
                throw new NotEnoughCredit("You dont have enough credit");
            }

            customer.setCredit(customer.getCredit() - receiptDto.getTotalAmount());
            customerService.update(customer);
            updateSpecialistCash(specialist, receiptDto.getTotalAmount());
            order.setStatus(OrderStatus.PAID);
            orderService.update(order);
            specialistScoreUpdate(specialist, receiptDto);
            if (specialist.getScore() < 0) {
                specialist.setSpecialistStatus(SpecialistStatus.DISABLED);
            }
            specialistService.update(specialist);
        } catch (Exception e) {
            throw new NotEnoughCreditException("An error occured while trying to pay from credit ");
        }
    }

    boolean canPayForOrder(ReceiptDto receiptDto) throws SuggestionOperationException {
        Suggestions suggestions = suggestionsService.findSuggestionsByNameOfOrderAndSpecialist(receiptDto.getNameOfOrder(), receiptDto.getSpecialistFirstName(), receiptDto.getSpecialistLastName());
        if (suggestions != null) {
            if (LocalTime.now().isAfter(suggestions.getWorkTime()) && LocalDate.now().isAfter(suggestions.getSuggestedDate())) {
                return true;
            } else {
                throw new InvalidDateException("Date is not valid ! ");
            }
        } else {
            throw new SuggestionNotFound("Suggestion Not Found ");
        }
    }

    @Override
    public boolean processPayment(CreditCardDto creditCardDto, ReceiptDto receiptDto) {
        return validationUtil.isValid(creditCardDto) && validationUtil.isValid(receiptDto);
    }

    private void specialistScoreUpdate(Specialist specialist, ReceiptDto receiptDto) throws SuggestionOperationException, SpecialistOperationException {
        LocalDateTime timeOfService = LocalDateTime.of(receiptDto.getDateOfService(), receiptDto.getTimeOfService());
        long lateHours = ChronoUnit.HOURS.between(timeOfService, LocalDateTime.now());
        specialist.setScore(specialist.getScore() - (double) lateHours);
    }

    private void updateSpecialistCash(Specialist specialist, Double totalAmount) throws SpecialistOperationException {
        specialist.setCash((totalAmount * 70) / 100);
        specialistService.update(specialist);
    }

}
