package ch.axa.ita.em.eventbackend.event;

import ch.axa.ita.em.eventbackend.model.Event;
import ch.axa.ita.em.eventbackend.repository.EventRepository;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
import ch.axa.ita.em.eventbackend.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private ParticipantRepository participantRepository;

    @InjectMocks
    private EventService eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetEventsSuccess() {
        Event event1 = new Event();
        Event event2 = new Event();
        when(eventRepository.findAll()).thenReturn(Arrays.asList(event1, event2));
        List<Event> events = eventService.getEvents();
        assertNotNull(events);
        assertEquals(2, events.size());
    }
    @Test
    void testGetEventsThrowsException() {
        when(eventRepository.findAll()).thenThrow(new RuntimeException("DB error"));
        assertThrows(IllegalArgumentException.class, () -> eventService.getEvents());
    }
    @Test
    void testCreateEventSuccess() {
        Event event = new Event();
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        Event createdEvent = eventService.createEvent(event);
        assertNotNull(createdEvent);
    }
    @Test
    void testCreateEventThrowsException() {
        when(eventRepository.save(any(Event.class))).thenThrow(new RuntimeException("DB error"));
        assertThrows(IllegalArgumentException.class, () -> eventService.createEvent(new Event()));
    }
    @Test
    void testGetEventByIdSuccess() {
        Event event = new Event();
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
        Event foundEvent = eventService.getEventById(1L);
        assertNotNull(foundEvent);
    }
    @Test
    void testGetEventByIdThrowsException() {
        when(eventRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> eventService.getEventById(1L));
    }
    @Test
    void testDeleteByIdSuccess() {
        doNothing().when(participantRepository).deleteByEventId(anyLong());
        doNothing().when(eventRepository).deleteById(anyLong());
        assertDoesNotThrow(() -> eventService.deleteById(1L));
    }
    @Test
    void testDeleteByIdThrowsException() {
        doThrow(new RuntimeException("DB error")).when(eventRepository).deleteById(anyLong());
        assertThrows(IllegalArgumentException.class, () -> eventService.deleteById(1L));
    }
    @Test
    void testUpdateEventSuccess() {
        Event event = new Event();
        when(eventRepository.findById(anyLong())).thenReturn(Optional.of(event));
        when(eventRepository.save(any(Event.class))).thenReturn(event);
        Event updatedEvent = eventService.updateEvent(1L, new Event());
        assertNotNull(updatedEvent);
    }
    @Test
    void testUpdateEventThrowsException() {
        when(eventRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> eventService.updateEvent(1L, new Event()));
    }
}