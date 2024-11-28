package org.practice.simplehomeorderservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditCardDto {
    private String cardNumber;

    private String expiryMonth;

    private String expiryYear;

    private String cvv2;

    private String captcha;
}
