package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.CreditCardDto;
import org.practice.simplehomeorderservice.entities.CreditCard;

public interface CreditCardService {
    CreditCardDto add(CreditCard creditCard);

    void update(CreditCardDto creditCardDto);

    void delete(CreditCardDto creditCardDto);

    CreditCardDto findByCardNumber(String cardNumber);

    CreditCardDto findByCardNumberAndCvv2(String cardNumber, String cvv2);

}
