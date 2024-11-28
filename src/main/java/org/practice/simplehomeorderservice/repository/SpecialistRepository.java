package org.practice.simplehomeorderservice.repository;


import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.enumerations.SpecialistStatus;
import org.practice.simplehomeorderservice.specification.SpecialistSpecification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpecialistRepository extends JpaRepository<Specialist, Integer>, JpaSpecificationExecutor<Specialist> {

    @Query("SELECT s FROM Specialist s WHERE s.specialistStatus = :specialistStatus")
    List<Specialist> findSpecialistByStatus(@Param("specialistStatus") SpecialistStatus status);

    @Query("SELECT s FROM Specialist s WHERE s.id = :id")
    Specialist findSpecialistById(@Param("id") Integer id);

    @Query("SELECT s FROM Specialist s WHERE s.firstName = :firstName AND s.lastName = :lastName")
    Specialist findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);

    @Query("SELECT s FROM Specialist s ORDER BY s.firstName ASC")
    List<Specialist> displayByFirstNameASC();

    @Query("SELECT s FROM Specialist s ORDER BY s.lastName ASC")
    List<Specialist> displayByLastNameASC();

    @Query("SELECT s FROM Specialist s ORDER BY s.score ASC")
    List<Specialist> displayByCreditASC();

    @Query("SELECT s FROM Specialist s ORDER BY s.score DESC")
    List<Specialist> displayByCreditDESC();

    List<Specialist> findAll(SpecialistSpecification specification);
}

