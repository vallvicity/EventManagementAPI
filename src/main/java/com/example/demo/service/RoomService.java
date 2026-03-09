package com.example.demo.service;

import com.example.demo.entity.Room;
import com.example.demo.repository.RoomRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room getOneRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found."));
    }

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Room updateRoom(Long id, @NotNull Room room) {
        Room roomToUpdate = roomRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Room not found"));

        if (room.getName() != null) {
            roomToUpdate.setName(room.getName());
        }
        if (room.getCapacity() != 0) {
            roomToUpdate.setCapacity(room.getCapacity());
        }
        if (room.getHotel() != null) {
            roomToUpdate.setHotel(room.getHotel());
        }

        return roomRepository.save(roomToUpdate);
    }

    public void deleteOneRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
