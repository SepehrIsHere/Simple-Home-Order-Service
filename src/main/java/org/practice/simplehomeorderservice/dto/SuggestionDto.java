package org.practice.simplehomeorderservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuggestionDto {
    private Double suggestedPrice;
    private LocalDate suggestedDate;
    private LocalTime suggestedTime;
    private String nameOfOrder;
    private String specialistFirstName;
    private String specialistLastName;

}
