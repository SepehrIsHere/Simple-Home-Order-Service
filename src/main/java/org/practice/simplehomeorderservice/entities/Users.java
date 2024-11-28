package org.practice.simplehomeorderservice.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.practice.simplehomeorderservice.enumerations.Role;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
@SuperBuilder
@Inheritance(strategy = InheritanceType.JOINED)
public class Users extends BaseEntity {

    @Column(unique = true)
    @NotBlank(message = "first name cant be empty")
    @Size(min = 3, max = 45, message = "Invalid first name")
    private String firstName;

    @Column(unique = true)
    @NotBlank(message = "last name cant be empty")
    @Size(min = 3, max = 40, message = "Invalid last name")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "email cant be empty")
    @Email(message = "Invalid email format ! please enter a correct email")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "username cant be blank")
    @Size(min = 2, max = 30, message = "username must be bigger than 2 and smaller than 30 characters")
    private String username;

    @NotBlank(message = "password cant be blank")
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]+$", message = "Password must contain both letters and numbers")
    @Size(min = 8, message = "password cant be smaller than 8 characters ! ")
    private String password;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "role cant be null")
    private Role role;

    @Column
    private Boolean isActive;

    @Column
    private String verificationCode;
}

