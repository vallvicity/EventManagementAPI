package com.example.demo.controller;

import com.example.demo.entity.Event;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventService eventService;

    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        return eventService.createEvent(event);

    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/{id}")
    public Event getEvent(@PathVariable Long id) {
        return eventService.getOneEvent(id);
    }

    @PutMapping("/{id}")
    public Event updateEvent( @PathVariable Long id, @RequestBody Event event) {
        return eventService.updateEvent(id, event);
    }


    @DeleteMapping
    public void deleteEvent(@RequestBody Event event) {
        Long idFound = event.getId();
        eventService.deleteOneEvent(idFound);

    }
}
