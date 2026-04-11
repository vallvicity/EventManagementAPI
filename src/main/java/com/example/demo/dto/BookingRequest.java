package com.example.demo.dto;


import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
public class BookingRequest {
    private Long personId;
    private Long roomId;
}
