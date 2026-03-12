package com.example.demo.service;

import com.example.demo.entity.Hotel;
import com.example.demo.entity.Room;
import com.example.demo.repository.HotelRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HotelService {

    private HotelRepository hotelRepository;
    private RoomRepository roomRepository;

    public HotelService(HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel getOneHotel(Long id) {
        return hotelRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found."));
    }

    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    public Hotel updateHotel(Long id, Hotel hotel) {
        Hotel hotelToUpdate = this.getOneHotel(id);

        if(hotel.getName() != null) {
            hotelToUpdate.setName(hotel.getName());
        }
        if(hotel.getCategory()!= 0) {
            hotelToUpdate.setCategory(hotel.getCategory());
        }
        if (hotel.getRooms() != null) {
            hotelToUpdate.setRooms(hotel.getRooms());
        }

        return hotelRepository.save(hotelToUpdate);
    }

    public void deleteOneHotel(Long id) {
        if(!hotelRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Hotel not found");
        }
        hotelRepository.deleteById(id);
    }

    public Hotel addRoomsToHotel(Hotel hotel, int numberOfRooms, Room roomToAdd) {
        List<Room> hotelRooms = hotel.getRooms();
        for (int i = 0; i < numberOfRooms; i++) {
            Room newRoom = roomRepository.save(roomToAdd);
            hotelRooms.add(newRoom);
        }
        hotel.setRooms(hotelRooms);
        return hotelRepository.save(hotel);
    }
}
