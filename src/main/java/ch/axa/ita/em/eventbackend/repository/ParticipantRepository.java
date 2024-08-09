package ch.axa.ita.em.eventbackend.repository;

import ch.axa.ita.em.eventbackend.model.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
    List<Participant> findByEventId(int eventId);
}

