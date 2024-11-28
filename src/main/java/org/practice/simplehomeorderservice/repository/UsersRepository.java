package org.practice.simplehomeorderservice.repository;


import org.practice.simplehomeorderservice.entities.Users;
import org.practice.simplehomeorderservice.enumerations.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> , JpaSpecificationExecutor<Users> {
    @Query("SELECT u FROM Users u WHERE u.id = :id")
    Users findUserById(@Param("id") int id);

    @Query("SELECT u FROM Users u WHERE u.username = :username")
    Optional<Users> findUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM Users u WHERE u.email = :email")
    Optional<Users> findByEmail(@Param("email") String email);

    @Query("SELECT u FROM Users u WHERE u.verificationCode = :verificationCode")
    Optional<Users> findByVerificationCode(@Param("verificationCode") String verificationCode);

    @Query("SELECT u FROM Users u WHERE u.firstName = :firstName AND u.lastName = :lastName")
    Users findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT u FROM Users u WHERE u.role = :role")
    List<Users> findByRole(@Param("role") Role role);

    @Query("SELECT u FROM Users u ORDER BY u.email ASC")
    List<Users> findAllOrderByEmailAsc();

    @Query("SELECT u FROM Users u ORDER BY u.email DESC")
    List<Users> findAllOrderByEmailDesc();

    @Query("SELECT u FROM Users u ORDER BY u.firstName ASC")
    List<Users> findAllOrderByFirstNameAsc();

    @Query("SELECT u FROM Users u ORDER BY u.lastName ASC")
    List<Users> findAllOrderByLastNameAsc();
}
