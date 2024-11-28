package org.practice.simplehomeorderservice.dto;

import lombok.*;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.SubTask;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecialistDto {
    private String firstname;
    private String lastname;
    private String username;
    private String password;
    private String email;
    private double score;
    private List<SubTask> specialistSubTask;
    private List<Order> specialistOrders;
}
