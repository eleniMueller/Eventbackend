package ch.axa.ita.em.eventbackend.event;

import ch.axa.ita.em.eventbackend.controller.EventController;
import ch.axa.ita.em.eventbackend.model.Event;
import ch.axa.ita.em.eventbackend.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventControllerTest {
    @Mock
    private EventService eventService;
    @InjectMocks
    private EventController eventController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testGetAllEventsSuccess() {
        Event event1 = new Event();
        Event event2 = new Event();
        when(eventService.getEvents()).thenReturn(Arrays.asList(event1, event2));
        ResponseEntity<List<Event>> response = eventController.getAllEvents();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
    }
    @Test
    void testGetAllEventsNoContent() {
        when(eventService.getEvents()).thenReturn(null);
        ResponseEntity<List<Event>> response = eventController.getAllEvents();
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
    @Test
    void testGetEventByIdSuccess() {
        Event event = new Event();
        when(eventService.getEventById(anyLong())).thenReturn(event);
        ResponseEntity<Event> response = eventController.getEventById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testGetEventByIdNotFound() {
        when(eventService.getEventById(anyLong())).thenReturn(null);
        ResponseEntity<Event> response = eventController.getEventById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void testCreateEventSuccess() {
        Event event = new Event();
        when(eventService.createEvent(any(Event.class))).thenReturn(event);
        ResponseEntity<Event> response = eventController.createEvent(new Event());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testCreateEventBadRequest() {
        when(eventService.createEvent(any(Event.class))).thenReturn(null);
        ResponseEntity<Event> response = eventController.createEvent(new Event());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
    @Test
    void testUpdateEventSuccess() {
        Event event = new Event();
        when(eventService.updateEvent(anyLong(), any(Event.class))).thenReturn(event);
        ResponseEntity<Event> response = eventController.updateEvent(1L, new Event());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
    }
    @Test
    void testUpdateEventNotFound() {
        doThrow(new IllegalArgumentException("Event not found")).when(eventService).updateEvent(anyLong(), any(Event.class));
        ResponseEntity<Event> response = eventController.updateEvent(1L, new Event());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
    @Test
    void testUpdateEventInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(eventService).updateEvent(anyLong(), any(Event.class));
        ResponseEntity<Event> response = eventController.updateEvent(1L, new Event());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
    @Test
    void testDeleteEventSuccess() {
        doNothing().when(eventService).deleteById(anyLong());
        ResponseEntity<Event> response = eventController.deleteEvent(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
    @Test
    void testDeleteEventInternalServerError() {
        doThrow(new RuntimeException("DB error")).when(eventService).deleteById(anyLong());
        ResponseEntity<Event> response = eventController.deleteEvent(1L);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}