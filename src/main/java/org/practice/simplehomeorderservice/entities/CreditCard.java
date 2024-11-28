package org.practice.simplehomeorderservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.practice.simplehomeorderservice.enumerations.Bank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class CreditCard extends BaseEntity {
    @Column(unique = true)
    @NotBlank
    @Size(min = 12, max = 16, message = "card number cant be smaller than 12 digits and bigger than 16")
    private String cardNumber;

    @NotNull(message = "expiry month is invalid ")
    private int expireMonth;

    @NotNull(message = "expiry year is invalid")
    private int expireYear;

    @Column(unique = true)
    @NotNull
    private int cvv2;

    @Enumerated(EnumType.STRING)
    private Bank bank;
}
