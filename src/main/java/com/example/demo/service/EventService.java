package com.example.demo.service;
import com.example.demo.entity.Event;
import com.example.demo.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(Event event) {
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

        if(event.getMaxCapacity() != 0) {
            eventFound.setMaxCapacity(event.getMaxCapacity());
        }

        return eventRepository.save(eventFound);

    }

    public void deleteOneEvent(Long id) {
        //Event eventFound = eventRepository.findById(id).orElseThrow();
        eventRepository.deleteById(id);
    }


}
