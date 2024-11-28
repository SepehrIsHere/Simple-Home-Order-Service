package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.SpecialistDto;
import org.practice.simplehomeorderservice.enumerations.SpecialistStatus;

import java.io.File;

public interface SpecialistEditService {
    void updateFirstName(SpecialistDto specialistDto, String newFirstName);

    void updateLastName(SpecialistDto specialistDto, String newLastName);

    void updateUsername(SpecialistDto specialistDto, String newUsername);

    void updatePassword(SpecialistDto specialistDto, String newPassword);

    void addPersonalImage(SpecialistDto specialistDto, File filepath);

    void removePersonalImage(SpecialistDto specialistDto);

    void changeSpecialistStatus(SpecialistDto specialistDto, SpecialistStatus newStatus);


}
