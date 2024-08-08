//package ch.axa.ita.em.eventbackend.event;
//
//import ch.axa.ita.em.eventbackend.controller.EventController;
//import ch.axa.ita.em.eventbackend.model.Event;
//import ch.axa.ita.em.eventbackend.service.EventService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.util.Arrays;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class EventControllerTest {
//
//    @Mock
//    private EventService eventService;
//
//    @InjectMocks
//    private EventController eventController;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testGetAllEvents() {
//        Event event1 = new Event();
//        event1.setTitle("Event 1");
//
//        Event event2 = new Event();
//        event2.setTitle("Event 2");
//
//        List<Event> events = Arrays.asList(event1, event2);
//
//        when(eventService.getEvents()).thenReturn(events);
//
//        ResponseEntity<List<Event>> response = eventController.getAllEvents();
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(2, response.getBody().size());
//    }
//
//    @Test
//    public void testGetEventById() {
//        Event event = new Event();
//        event.setTitle("Event");
//
//        when(eventService.getEventById(1L)).thenReturn(event);
//
//        ResponseEntity<Event> response = eventController.getEventById(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Event", response.getBody().getTitle());
//    }
//
//    @Test
//    public void testCreateEvent() {
//        Event event = new Event();
//        event.setTitle("New Event");
//
//        when(eventService.createEvent(any(Event.class))).thenReturn(event);
//
//        ResponseEntity<Event> response = eventController.createEvent(event);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("New Event", response.getBody().getTitle());
//    }
//
//    @Test
//    public void testUpdateEvent() {
//        Event event = new Event();
//        event.setTitle("Updated Event");
//
//        when(eventService.updateEvent(any(Long.class), any(Event.class))).thenReturn(event);
//
//        ResponseEntity<Event> response = eventController.updateEvent(1L, event);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Updated Event", response.getBody().getTitle());
//    }
//
//    @Test
//    public void testDeleteEvent() {
//        ResponseEntity<Event> response = eventController.deleteEvent(1L);
//
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//    }
//}