package org.practice.simplehomeorderservice.service;



import org.practice.simplehomeorderservice.dto.SpecialistDto;
import org.practice.simplehomeorderservice.dto.SubTaskDto;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;

import java.util.List;

public interface SpecialistService {
    SpecialistDto signUp(SpecialistDto specialistDto) throws SpecialistOperationException;

    void add(Specialist specialist) throws SpecialistOperationException;

    void update(Specialist specialist) throws SpecialistOperationException;

    void delete(Specialist specialist) throws SpecialistOperationException;

    List<Specialist> findAll() throws SpecialistOperationException;

    Specialist findById(int id) throws SpecialistOperationException;

    Specialist findByFirstNameAndLastName(String firstName, String lastName) throws SpecialistOperationException;

    boolean checkSpecialistImage(SpecialistDto specialistDto) throws SpecialistOperationException;

    List<SubTaskDto> specialistSubTasks(SpecialistDto specialistDto);

}
