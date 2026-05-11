package com.example.demo.dto.response;

import com.example.demo.entity.Registration;
import com.example.demo.entity.User;

import java.time.LocalDate;
import java.util.List;

public record EventResponse(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        int maxCapacity,

        //TODO: Create UserResponse & RegistrationResponse DTOs
        User organizer,
        List<Registration> registrations

) {}
