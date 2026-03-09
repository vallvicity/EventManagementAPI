package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int capacity;
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public Room(){

    }
    public Room(String name, int capacity, Hotel hotel) {
        this.name = name;
        this.capacity = capacity;
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
