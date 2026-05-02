package com.example.demo.dto.response;

import com.example.demo.entity.Room;

import java.util.List;

public record HotelResponse(
        Long id,
        String name,
        int Category,
        List<Room> rooms
) {}
