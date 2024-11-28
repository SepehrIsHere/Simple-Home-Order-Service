package org.practice.simplehomeorderservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.practice.simplehomeorderservice.enumerations.SpecialistStatus;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
public class Specialist extends Users {

    @Column
    private byte[] personalImage;

    @Enumerated(EnumType.STRING)
    private SpecialistStatus specialistStatus;

    @Column
    @Min(value = 0, message = "score cant be lower than 0")
    @Max(value = 5, message = "score cant be larger than 5")
    private Double score;

    @Column
    private Double cash;

    @OneToMany
    private List<SubTask> subTasks;

    @OneToMany
    private List<Order> orders;

    @OneToMany
    private List<Comment> comments;

}
