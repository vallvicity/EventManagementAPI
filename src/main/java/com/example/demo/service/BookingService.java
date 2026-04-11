package com.example.demo.service;

import com.example.demo.entity.Booking;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class BookingService {

    private BookingRepository bookingRepository;
    private PersonRepository personRepository;
    private RoomRepository roomRepository;

    public BookingService(BookingRepository bookingRepository, PersonRepository personRepository,
                          RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.personRepository = personRepository;
        this.roomRepository = roomRepository;
    }

    public Booking createBooking(Booking booking, Long personId, Long roomId) {
        return booking;
    }
}
