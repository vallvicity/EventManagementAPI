package com.example.demo.controller;

import com.example.demo.dto.RegistrationRequest;
import com.example.demo.entity.Registration;
import com.example.demo.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/registrations")
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public List<Registration> getAllRegistrations() {
        return registrationService.getAllRegistrations();
    }

    @GetMapping("/{id}")
    public Registration getOneRegistration(@PathVariable Long id) {
        return registrationService.getOneRegistration(id);
    }

    @PostMapping
    public Registration createRegistration(@Valid @RequestBody RegistrationRequest request) {
        return registrationService.createRegistration(request);
    }

    @PutMapping("/{id}")
    public Registration updateRegistration(@PathVariable Long id, @RequestBody RegistrationRequest request) {
        return registrationService.updateRegistration(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistration(@PathVariable Long id) {
        registrationService.deleteRegistration(id);
        return ResponseEntity.noContent().build();
    }





}
