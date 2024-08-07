package ch.axa.ita.em.eventbackend.service;

import ch.axa.ita.em.eventbackend.model.Event;
import ch.axa.ita.em.eventbackend.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        System.out.println(event);

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

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public void updateEvent(Event existingEvent) {
        try {
            Optional<Event> optionalEvent = eventRepository.findById(existingEvent.getEvent_id());
            if (optionalEvent.isPresent()) {
                Event eventToUpdate = optionalEvent.get();
                eventToUpdate.setTitle(existingEvent.getTitle());
                eventToUpdate.setDescription(existingEvent.getDescription());
                eventToUpdate.setDate(existingEvent.getDate());
                eventToUpdate.setDuration(existingEvent.getDuration());
                eventToUpdate.setLocation(existingEvent.getLocation());
                eventToUpdate.setOwner_email(existingEvent.getOwner_email());
                eventToUpdate.setParticipant_limit(existingEvent.getParticipant_limit());
                eventToUpdate.setCategory_id(existingEvent.getCategory_id());
                eventToUpdate.setKeywords(existingEvent.getKeywords());
                eventToUpdate.setImage(existingEvent.getImage());
                eventRepository.save(eventToUpdate);
            } else {
                throw new IllegalArgumentException("Event with ID " + existingEvent.getEvent_id() + " does not exist.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}