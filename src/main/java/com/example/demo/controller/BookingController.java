package com.example.demo.controller;

import com.example.demo.dto.BookingRequest;
import com.example.demo.entity.Booking;
import com.example.demo.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/{personId}/{roomId}")
    public Booking createBooking(@RequestBody BookingRequest bookingRequest, @PathVariable Long personId, @PathVariable Long roomId) {
        return bookingService.createBooking(bookingRequest, personId, roomId);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getOneBooking(@PathVariable Long id) {
        return bookingService.getOneBooking(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }

}
