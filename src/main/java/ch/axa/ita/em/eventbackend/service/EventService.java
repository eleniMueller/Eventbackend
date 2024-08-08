package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Event;
import ch.axa.ita.em.eventbackend.repository.EventRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents() {
        try {
            return eventRepository.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Event createEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public Event getEventById(Long id) {
        try {
            return eventRepository.findById(id).orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteById(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Transactional
    public Event updateEvent(Long id, Event newEvent) {
        Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Event with ID " + id + " does not exist.", 1));
        newEvent.setEvent_id(existingEvent.getEvent_id());
        return eventRepository.save(newEvent);
    }
}