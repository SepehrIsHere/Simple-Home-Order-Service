package org.practice.simplehomeorderservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.SpecialistDto;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.enumerations.SpecialistStatus;
import org.practice.simplehomeorderservice.exception.IllegalImageException;
import org.practice.simplehomeorderservice.exception.SpecialistNotFoundException;
import org.practice.simplehomeorderservice.repository.SpecialistRepository;
import org.practice.simplehomeorderservice.service.SpecialistEditService;
import org.practice.simplehomeorderservice.util.MapperUtil;
import org.practice.simplehomeorderservice.util.PersonalImageUtil;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class SpecialistEditServiceImpl implements SpecialistEditService {
    private final SpecialistRepository specialistRepository;
    private PersonalImageUtil personalImageUtil;
    private MapperUtil mapperUtil;

    @Override
    public void updateFirstName(SpecialistDto specialistDto, String newFirstName) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            specialist.setFirstName(newFirstName);
            specialistRepository.save(specialist);
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }

    @Override
    public void updateLastName(SpecialistDto specialistDto, String newLastName) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            specialist.setLastName(newLastName);
            specialistRepository.save(specialist);
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }

    @Override
    public void updateUsername(SpecialistDto specialistDto, String newUsername) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            specialist.setUsername(newUsername);
            specialistRepository.save(specialist);
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }

    @Override
    public void updatePassword(SpecialistDto specialistDto, String newPassword) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            specialist.setPassword(newPassword);
            specialistRepository.save(specialist);
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }

    @Override
    public void changeSpecialistStatus(SpecialistDto specialistDto, SpecialistStatus newStatus) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            specialist.setSpecialistStatus(newStatus);
            specialistRepository.save(specialist);
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }

    @Override
    public void addPersonalImage(SpecialistDto specialistDto, File filepath) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            if (personalImageUtil.isImageValid(filepath)) {
                specialist.setPersonalImage(personalImageUtil.writeImage(filepath));
                specialistRepository.save(specialist);
            } else {
                throw new IllegalImageException("Image is not valid ! ");
            }
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }

    @Override
    public void removePersonalImage(SpecialistDto specialistDto) {
        Specialist specialist = specialistRepository.findByFirstNameAndLastName(specialistDto.getFirstname(), specialistDto.getLastname());
        if (specialist != null) {
            specialist.setPersonalImage(null);
            specialistRepository.save(specialist);
        } else {
            throw new SpecialistNotFoundException("Specialist Not Found !");
        }
    }
}
