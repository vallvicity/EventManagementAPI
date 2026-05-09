package com.example.demo.service;
import com.example.demo.dto.EventRequest;
import com.example.demo.entity.Event;
import com.example.demo.entity.User;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository eventRepository;
    private UserRepository userRepository;

    public EventService(EventRepository eventRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.userRepository = userRepository;
    }

    public Event createEvent(EventRequest request) {
        validateEventDates(request.getStartDate(), request.getEndDate());

        User user = userRepository.findById(request.getOrganizerId())
                .orElseThrow(() -> new ResourceNotFoundException("Organizer with id " + request.getOrganizerId() + " not found"));

        Event event = new Event();
        event.setName(request.getName());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setMaxCapacity(request.getMaxCapacity());
        event.setOrganizer(user);

        return eventRepository.save(event);
    }

    public Event getOneEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event with id "
                        + id + " not found"));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public Event updateEvent(Long id, EventRequest request) {
        Event eventFound = eventRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        if(request.getName() != null) {
            eventFound.setName(request.getName());
        }
        //TODO: startDate cannot be after endDate
//        if(request.getStartDate() != null) {
//            //event.setEndDate(eventFound.getEndDate());
//            validateEventDates(request.getStartDate(), event.getEndDate());
//            eventFound.setStartDate(event.getStartDate());
//        }
//
//        if(event.getEndDate() != null) {
//            event.setStartDate(eventFound.getStartDate());
//            validateEventDates(event.getStartDate(), event.getEndDate());
//            eventFound.setEndDate(event.getEndDate());
//        }
        if(request.getStartDate() != null || request.getEndDate() != null) {
            LocalDate newStart = request.getStartDate() != null ? request.getStartDate() : eventFound.getStartDate();
            LocalDate newEnd = request.getEndDate() != null ? request.getEndDate() : eventFound.getEndDate();

            validateEventDates(newStart, newEnd);
            eventFound.setStartDate(newStart);
            eventFound.setEndDate(newEnd);
        }

        if(request.getMaxCapacity() != null) {
            eventFound.setMaxCapacity(request.getMaxCapacity());
        }

        return eventRepository.save(eventFound);

    }

    public void deleteOneEvent(Long id) {
        if(!eventRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        eventRepository.deleteById(id);
    }

    private void validateEventDates(LocalDate startDate, LocalDate endDate) {
        if (startDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("You cannot create an Event with start date in the past");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("The end of the Event cannot be before the start date");
        }
    }

}
