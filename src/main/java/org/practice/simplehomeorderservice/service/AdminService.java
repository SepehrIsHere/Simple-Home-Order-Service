package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.SubTask;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.SubTaskOperationException;

public interface AdminService {

    void changeBasePriceOfSubTask(SubTask subTask, Double basePrice);

    void changeDescriptionOfSubTask(SubTask subTask, String description);

    void removeSpecialistFromSubTask(Specialist specialist, SubTask subTask) throws SpecialistOperationException, SubTaskOperationException;

    void approveSpecialist(String specialistFirstName,String specialistLastName) throws SpecialistOperationException;

    void addSubTaskToSpecialist(String specialistFirstName, String specialistLastName, String subTaskName) throws SpecialistOperationException;

    void removeSubTaskFromSpecialist(String specialistFirstName, String specialistLastName, String subTaskName) throws SpecialistOperationException;
}
