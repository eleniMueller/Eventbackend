package ch.axa.ita.em.eventbackend.controller;

import ch.axa.ita.em.eventbackend.model.Event;
import ch.axa.ita.em.eventbackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/events")
@CrossOrigin(origins = "http://localhost:3000")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getEvents();
        if (events != null && !events.isEmpty()) {
            Logger.getLogger("All events fetched successfully");
            return ResponseEntity.ok(events);
        } else {
            Logger.getLogger("No events found");
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Long id) {
        Event event = eventService.getEventById(id);
        if (event != null) {
            Logger.getLogger("Event with ID " + id + "fetched successfully");
            return ResponseEntity.ok(event);
        } else {
            Logger.getLogger("Event with ID " + id + "not found");
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody Event event) {
        Event createdEvent = eventService.createEvent(event);
        if (createdEvent != null) {
            Logger.getLogger("Event created successfully");
            return ResponseEntity.ok(createdEvent);
        } else {
            Logger.getLogger("Event creation failed");
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id, @RequestBody Event newEvent) {
        try {
            Event updatedEvent = eventService.updateEvent(id, newEvent);
            Logger.getLogger("Event with ID " + id + " updated successfully");
            return ResponseEntity.ok(updatedEvent);
        } catch (IllegalArgumentException e) {
            Logger.getLogger("Event with ID " + id + " not found");
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            Logger.getLogger("Event with ID " + id + " could not be updated");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long id) {
        try {
            eventService.deleteById(id);
            Logger.getLogger("Event with ID " + id + " deleted successfully");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            Logger.getLogger("Event with ID " + id + " could not be deleted");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

