package org.practice.simplehomeorderservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.practice.simplehomeorderservice.enumerations.OrderStatus;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(unique = true)
    @NotBlank(message = "Order must have a name!")
    private String nameOfOrder;

    @Column
    @Min(value =  0,message = "suggested price cant be smaller than 0 ")
    @NotNull(message = "Suggested price cant be null")
    private Double suggestedPrice;

    @Temporal(TemporalType.DATE)
    @NotNull(message = "date of service cant be null")
//    @Future(message = "the date cannot be earlier than today's date")
    private LocalDate dateOfService;

    @Column
    @NotBlank(message = "description cant be blank!")
    @Size(min = 5, max = 1000, message = "description cant be smaller than 25 and larger than 1000 !")
    private String description;

    @Enumerated
    private OrderStatus status;

    @ManyToOne
    private SubTask subTask;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    private Specialist specialist;

    @OneToMany
    private List<Suggestions> suggestions;
}
