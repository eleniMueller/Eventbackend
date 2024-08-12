package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Event;
import ch.axa.ita.em.eventbackend.repository.EventRepository;
import ch.axa.ita.em.eventbackend.repository.ParticipantRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private ParticipantRepository participantRepository;

    public List<Event> getEvents() {
        try {
            return eventRepository.findAll();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());

        }
    }

    public Event createEvent(Event event) {
        try {
            return eventRepository.save(event);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public Event getEventById(Long id) {
        try {
            return eventRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public void deleteById(Long id) {
        try {
            participantRepository.deleteByEventId(id);
            eventRepository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Transactional
    public Event updateEvent(Long id, Event newEvent) {
        try {
            Event existingEvent = eventRepository.findById(id).orElseThrow(() -> new EmptyResultDataAccessException("Event with ID " + id + " does not exist.", 1));
            newEvent.setEvent_id(existingEvent.getEvent_id());
            return eventRepository.save(newEvent);
        } catch (EmptyResultDataAccessException e) {
            throw new IllegalArgumentException(e.getMessage());
        }

    }
}