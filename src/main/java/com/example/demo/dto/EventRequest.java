package com.example.demo.dto;

import com.example.demo.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class EventRequest {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private int maxCapacity;
    private User organizer;
}
