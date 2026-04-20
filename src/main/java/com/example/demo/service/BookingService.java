package com.example.demo.service;

import com.example.demo.dto.BookingRequest;
import com.example.demo.entity.Booking;
import com.example.demo.entity.Person;
import com.example.demo.entity.Room;
import com.example.demo.repository.BookingRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RoomRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

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

    //TODO: Booking for more than one person, set checkin, checkout etc
    public Booking createBooking(BookingRequest bookingRequest) {

        Long personId = bookingRequest.getPersonId();

        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Room room = roomRepository.findById(bookingRequest.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Set<Person> personsInBooking = new HashSet<>();
        personsInBooking.add(person);

        Booking booking = new Booking();
        booking.setRoom(room);
        booking.setPersons(personsInBooking);
        booking.setCheckIn(bookingRequest.getCheckIn());
        booking.setCheckout(bookingRequest.getCheckOut());

        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking getOneBooking(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found."));
    }

    //TODO: updateBooking

    public void deleteBooking(Long id) {
        if(bookingRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found.");
        }
        bookingRepository.deleteById(id);
    }
}
