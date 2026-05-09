package com.example.demo.service;

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;
    @Test
    void createEvent_shouldReturnSavedEvent_whenValidInput() {
        Event event = new Event();
        event.setName("Test event");

        when(eventRepository.save(event)).thenReturn(event);

        Event result = eventRepository.save(event);

        assertNotNull(result);
        assertEquals("Test event", result.getName());
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    void getOneEvent_shouldReturnEvent_whenEventExists() {
        Event event = new Event();
        event.setId(1L);
        event.setName("Test event");

        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        Event result = eventService.getOneEvent(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Test event", result.getName());
    }

    @Test
    void getOneEvent_shouldThrowException_whenEventDoesNotExist(){

        when(eventRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class,
                () -> eventService.getOneEvent(1L));
    }
}

