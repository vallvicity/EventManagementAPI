package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddRoomsToHotelRequest {

    private String name;
    private Integer capacity;
    private Long hotelId;

}
