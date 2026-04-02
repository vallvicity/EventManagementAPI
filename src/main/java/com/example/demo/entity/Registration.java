package com.example.demo.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "attendee_id")
    private Person attendee;
    private String registrationType;
    private String status;

    public Registration() {}

    public Registration(Event event, Person attendee, String registrationType, String status) {
        this.event = event;
        this.attendee = attendee;
        this.registrationType = registrationType;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public Person getAttendee() {
        return attendee;
    }

    public void setAttendee(Person attendee) {
        this.attendee = attendee;
    }

    public String getRegistrationType() {
        return registrationType;
    }

    public void setRegistrationType(String registrationType) {
        this.registrationType = registrationType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Registration that = (Registration) o;
        return Objects.equals(id, that.id) && Objects.equals(event, that.event) && Objects.equals(attendee, that.attendee) && Objects.equals(registrationType, that.registrationType) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, event, attendee, registrationType, status);
    }

    @Override
    public String toString() {
        return "Registration{" +
                "event=" + event +
                '}';
    }
}
