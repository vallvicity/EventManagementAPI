package com.example.demo.service;
import com.example.demo.dto.EventRequest;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
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

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventRequest request) {
        if (request.getStartDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("You cannot create an Event with start date in the past");
        }
        if (request.getStartDate().isAfter(request.getEndDate())) {
            throw new RuntimeException("The end of the Event cannot be before the start date");
        }

        Event event = new Event();
        event.setName(request.getName());
        event.setStartDate(request.getStartDate());
        event.setEndDate(request.getEndDate());
        event.setMaxCapacity(request.getMaxCapacity());
        event.setOrganizer(request.getOrganizer());

        return eventRepository.save(event);
    }

    public Event getOneEvent(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Transactional
    public Event updateEvent(Long id, Event event) {
        Event eventFound = eventRepository.findById(id)
                .orElseThrow(() ->new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found"));

        if(event.getName() != null) {
            eventFound.setName(event.getName());
        }
        //TODO: startDate cannot be after endDate
        if(event.getStartDate() != null) {
            eventFound.setStartDate(event.getStartDate());
        }

        if(event.getEndDate() != null) {
            eventFound.setEndDate(event.getEndDate());
        }

        //TODO: problem with max Capacity != 0
        if(event.getMaxCapacity() != 0) {
            eventFound.setMaxCapacity(event.getMaxCapacity());
        }

        return eventRepository.save(eventFound);

    }

    public void deleteOneEvent(Long id) {
        if(!eventRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Event not found");
        }
        eventRepository.deleteById(id);
    }

}
