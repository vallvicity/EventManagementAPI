package com.example.demo.service;

import com.example.demo.dto.request.RegistrationRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.Person;
import com.example.demo.entity.Registration;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.RegistrationRepository;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static com.example.demo.enums.Status.PENDING;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private PersonRepository personRepository;
    private EventRepository eventRepository;

    public RegistrationService(RegistrationRepository registrationRepository,
                               PersonRepository personRepository, EventRepository eventRepository) {
        this.registrationRepository = registrationRepository;
        this.personRepository = personRepository;
        this.eventRepository = eventRepository;
    }

    public Registration createRegistration(@NotNull RegistrationRequest request) {
        Person attendee = personRepository.findById(request.getAttendeeId())
                .orElseThrow(() -> new RuntimeException("Person not found"));

        Event event = eventRepository.findById(request.getEventId())
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (registrationRepository.existsByAttendeeAndEvent(attendee, event)) {
            throw new RuntimeException("Attendee already registered");
        }
        Registration registration = new Registration();
        registration.setAttendee(attendee);
        registration.setEvent(event);
        registration.setStatus(PENDING);


        return registrationRepository.save(registration);
    }

    public Registration getOneRegistration(Long id) {
        return registrationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registration not found"));
    }

    public List<Registration> getAllRegistrations() {
        return registrationRepository.findAll();
    }

    @Transactional
    public Registration updateRegistration(Long id, RegistrationRequest request) {
        Registration registrationToUpdate = this.getOneRegistration(id);
        if(request.getAttendeeId() != null) {
            Person person = personRepository.findById(request.getAttendeeId())
                    .orElseThrow(() ->new RuntimeException("Person not found"));

            registrationToUpdate.setAttendee(person);
        }

        if(request.getEventId() != null) {
            Event event = eventRepository.findById(request.getEventId())
                    .orElseThrow(() -> new RuntimeException("Event not found."));

            registrationToUpdate.setEvent(event);
        }

        if (request.getStatus() != null) {
            registrationToUpdate.setStatus(request.getStatus());
        }

        return registrationToUpdate;
    }

    public void deleteRegistration(Long id) {
        if(!registrationRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Registration not found");
        }
        registrationRepository.deleteById(id);
    }
}
