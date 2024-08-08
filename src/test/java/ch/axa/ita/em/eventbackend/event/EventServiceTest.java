//package ch.axa.ita.em.eventbackend.event;
//
//import ch.axa.ita.em.eventbackend.model.Event;
//import ch.axa.ita.em.eventbackend.repository.EventRepository;
//import ch.axa.ita.em.eventbackend.service.EventService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.springframework.dao.EmptyResultDataAccessException;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//
//public class EventServiceTest {
//
//    @Mock
//    private EventRepository eventRepository;
//
//    @InjectMocks
//    private EventService eventService;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    public void testUpdateEvent() {
//        Event existingEvent = new Event();
//        existingEvent.setEvent_id(1L);
//        existingEvent.setTitle("Old Title");
//
//        Event newEvent = new Event();
//        newEvent.setTitle("New Title");
//
//        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));
//        when(eventRepository.save(any(Event.class))).thenReturn(newEvent);
//
//        Event updatedEvent = eventService.updateEvent(1L, newEvent);
//
//        assertEquals("New Title", updatedEvent.getTitle());
//        assertEquals(1L, updatedEvent.getEvent_id());
//    }
//
//    @Test
//    public void testUpdateEventNotFound() {
//        when(eventRepository.findById(1L)).thenReturn(Optional.empty());
//
//        assertThrows(EmptyResultDataAccessException.class, () -> {
//            eventService.updateEvent(1L, new Event());
//        });
//    }
//}