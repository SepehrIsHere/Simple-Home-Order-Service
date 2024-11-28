package org.practice.simplehomeorderservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubTaskDto {
    private String subTaskName;
    private String subTaskDescription;
    private double basePrice;
    private String taskName;
}
