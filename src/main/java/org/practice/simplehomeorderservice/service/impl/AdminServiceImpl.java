package org.practice.simplehomeorderservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.SubTask;
import org.practice.simplehomeorderservice.enumerations.SpecialistStatus;
import org.practice.simplehomeorderservice.exception.SpecialistNotFoundException;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.SubTaskNotFoundException;
import org.practice.simplehomeorderservice.exception.SubTaskOperationException;
import org.practice.simplehomeorderservice.repository.SpecialistRepository;
import org.practice.simplehomeorderservice.repository.SubTaskRepository;
import org.practice.simplehomeorderservice.service.AdminService;
import org.practice.simplehomeorderservice.service.SpecialistService;
import org.practice.simplehomeorderservice.service.SubTaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final SpecialistService specialistService;
    private final SpecialistRepository specialistRepository;
    private final SubTaskRepository subTaskRepository;
    private final SubTaskService subTaskService;

    @Override
    public void addSubTaskToSpecialist(String specialistFirstName, String specialistLastName, String subTaskName) throws SpecialistOperationException {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistFirstName, specialistLastName);
        SubTask subTask = subTaskRepository.findByName(subTaskName);
        if (specialist != null) {
            if (subTask != null) {
                List<SubTask> specialistSubTasks = specialist.getSubTasks();
                if (specialistSubTasks != null && !specialistSubTasks.contains(subTask)) {
                    specialistSubTasks.add(subTask);
                    specialist.setSubTasks(specialistSubTasks);
                    specialistService.update(specialist);
                }
            } else {
                throw new SubTaskNotFoundException("SubTask Not Found ! ");
            }
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found ! ");
        }
    }

    @Override
    public void removeSubTaskFromSpecialist(String specialistFirstName, String specialistLastName, String subTaskName) throws SpecialistOperationException {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistFirstName, specialistLastName);
        SubTask subTask = subTaskRepository.findByName(subTaskName);
        if (specialist != null) {
            if (subTask != null) {
                List<SubTask> specialistSubTasks = specialist.getSubTasks();
                if (specialistSubTasks != null) {
                    specialistSubTasks.remove(subTask);
                    specialistService.update(specialist);
                }
            } else {
                throw new SubTaskNotFoundException("SubTask Not Found ! ");
            }
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found ! ");
        }
    }

    @Override
    public void changeBasePriceOfSubTask(SubTask subTask, Double basePrice) {
        try {
            subTask.setBasePrice(basePrice);
            subTaskService.update(subTask);
        } catch (Exception e) {
            System.out.println("An error occured while changing basePrice of a subtask " + e.getMessage());
        }
    }

    @Override
    public void changeDescriptionOfSubTask(SubTask subTask, String description) {
        try {
            subTask.setDescription(description);
            subTaskService.update(subTask);
        } catch (Exception e) {
            System.out.println("An error occured while changing description of a subtask " + e.getMessage());
        }
    }

    @Override
    public void approveSpecialist(String specialistFirstName, String specialistLastName) throws SpecialistOperationException {
        try {
            Specialist specialist = specialistService.findByFirstNameAndLastName(specialistFirstName, specialistLastName);
            if (specialist != null) {
                specialist.setSpecialistStatus(SpecialistStatus.APPROVED);
                specialistService.update(specialist);
            } else {
                throw new SpecialistNotFoundException("Specialist Not Found");
            }
        } catch (Exception e) {
            throw new SpecialistOperationException("An error occured approving Specialist", e);
        }
    }

    @Override
    public void removeSpecialistFromSubTask(Specialist specialist, SubTask subTask) throws SpecialistOperationException, SubTaskOperationException, SubTaskOperationException {
        List<Specialist> specialists = subTask.getSpecialists();
        boolean specialistFound = false;

        for (Specialist s : new ArrayList<>(specialists)) {
            if (s.equals(specialist)) {
                specialists.remove(s);
                specialistFound = true;
                break;
            }
        }
        if (!specialistFound) {
            throw new SpecialistNotFoundException("Specialist not found in the specified subtask.");
        }
        subTaskService.update(subTask);
        List<SubTask> subTasks = specialist.getSubTasks();
        if (subTasks != null) {
            subTasks.removeIf(s -> s.equals(subTask));
            specialistService.update(specialist);
        }
    }
}