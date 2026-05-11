package com.example.demo.dto.request;

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
    private Integer maxCapacity;
    private Long organizerId;
}
