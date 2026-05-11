package com.example.demo.entity;

import com.example.demo.enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Set;

@Entity
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate checkIn;
    private LocalDate checkout;
    private Status status;
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    //TODO: JoinColumn para el ManytoMAny & inicializar colección (new HashSet) para evitar NPE
    @ManyToMany
    private Set<Person> persons;

    @OneToOne
    @JoinColumn(name = "registration_id")
    private Registration registration;


    public Booking() {
    }

    public Booking(LocalDate checkIn, LocalDate checkout, Status status, Set<Person> persons, Room room, Registration registration) {
        this.checkIn = checkIn;
        this.checkout = checkout;
        this.status = status;
        this.persons = persons;
        this.room = room;
        this.registration = registration;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

    public void setCheckout(LocalDate checkout) {
        this.checkout = checkout;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public Registration getRegistration() {
        return registration;
    }

    public void setRegistration(Registration registration) {
        this.registration = registration;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
