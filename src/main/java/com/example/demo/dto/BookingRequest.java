package com.example.demo.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BookingRequest {
    private Long personId;
    private Long roomId;
    private LocalDate checkIn;
    private LocalDate checkOut;

}
