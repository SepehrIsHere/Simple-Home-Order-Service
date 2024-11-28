package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.CreditCardDto;
import org.practice.simplehomeorderservice.dto.ReceiptDto;

public interface PaymentService {
    void payWithCustomerCredit(String nameOfOrder);

    boolean processPayment(CreditCardDto creditCardDto, ReceiptDto receiptDto);
}
