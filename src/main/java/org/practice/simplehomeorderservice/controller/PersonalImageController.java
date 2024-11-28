package org.practice.simplehomeorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.exception.ImageOperationException;
import org.practice.simplehomeorderservice.service.SpecialistService;
import org.practice.simplehomeorderservice.util.PersonalImageUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/image")
@RequiredArgsConstructor
public class PersonalImageController {
    private final PersonalImageUtil personalImageUtil;
    private final SpecialistService specialistService;

    @PatchMapping("specialist/upload/{specialistFirstName}/{specialistLastName}")
    public ResponseEntity<String> uploadPersonalImage(@PathVariable String specialistFirstName
            , @PathVariable String specialistLastName
            , @RequestParam("imageFile") MultipartFile file) {
        try {
            File imageFile = convertMultipartFileToFile(file);
            if (!personalImageUtil.isImageValid(imageFile)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid image format or size too large.");
            }
            byte[] imageBytes = personalImageUtil.writeImage(imageFile);
            Specialist specialist = specialistService.findByFirstNameAndLastName(specialistFirstName, specialistLastName);
            specialist.setPersonalImage(imageBytes);
            specialistService.update(specialist);
            return ResponseEntity.ok("Image uploaded successfully ");
        } catch (Exception e) {
            throw new ImageOperationException("An error occured while trying to add personal image");
        }
    }

    @GetMapping("specialist/view/{specialistFirstName}/{specialistLastName}")
    ResponseEntity<byte[]> getPersonalImage(@PathVariable String specialistFirstName, @PathVariable String specialistLastName) {
        try {
            Specialist specialist = specialistService.findByFirstNameAndLastName(specialistFirstName, specialistLastName);
            byte[] personalImage = specialist.getPersonalImage();

            if (personalImage == null || personalImage.length == 0) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(personalImage);
        } catch (Exception e) {
            throw new ImageOperationException("An error occurred while trying to display personal image");
        }
    }


    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File file = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(file);
        return file;
    }
}
