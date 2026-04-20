package com.example.demo.repository;

import com.example.demo.entity.Event;
import com.example.demo.entity.Person;
import com.example.demo.entity.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<Registration, Long> {
    boolean existsByAttendeeAndEvent(Person attendee, Event event);
}
