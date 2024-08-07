package ch.axa.ita.em.eventbackend.repository;

import ch.axa.ita.em.eventbackend.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
